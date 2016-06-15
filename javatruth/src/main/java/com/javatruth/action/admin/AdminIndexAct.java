package com.javatruth.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.utils.StringUtils;
import com.common.web.Constants;
import com.common.web.WebSite;
import com.common.web.session.HttpSessionProvider;
import com.javatruth.entity.User;
import com.javatruth.service.IUserService;

@Controller
@RequestMapping("/admin/*")
public class AdminIndexAct {
	
	@Resource
	private IUserService userService;
	@Autowired
    private HttpSessionProvider session;
	
	@RequestMapping("index.html")
	public String adminIndex(HttpServletRequest request,Model model){		
		return WebSite.getAdminTemplate("index");
	}
	
	
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,Model model){		
		return WebSite.getAdminTemplate("login");
	}
	
	

	@RequestMapping(value = "login.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String login(String username,String password,HttpServletRequest request,HttpServletResponse response,ModelMap model){		
		WebSite.setParameters(request, model);
		System.out.println("username:"+username+",password:"+password);
		if(StringUtils.isBlank(username)){
			model.addAttribute("message", "用户名不能为空.");
			return WebSite.getAdminTemplate("login");
		}
		if(StringUtils.isBlank(password)){
			model.addAttribute("message", "密码不能为空.");
			return WebSite.getAdminTemplate("login");
		}
		User user =userService.findBySysUserName(username);
		if(user==null){
			model.addAttribute("message", "用户名或密码错误.");
			return WebSite.getAdminTemplate("login");
		}
		if (user.getUserType() == User.SYS_USER_TYPE&&user.getStatus()!=null&&user.getStatus().intValue()==User.STUTAS_NORMAL) {
			if (user != null && user.getUserPwd().equals(DigestUtils.md5Hex(password))) {

				/**
				 * 登录成功
				 */
				session.setAttribute(request, response, Constants.ADMIN_SESSION_KEY, user);
//				adminLogMng.save(user, userName + " 登录成功", AdminLog.TYPE_LOGIN, AdminLog.RESULT_SUCC);
				return "redirect:index.html";
			} else {
				model.addAttribute("message", "密码不正确！");
//				adminLogMng.save(user, userName + " 密码不正确", AdminLog.TYPE_LOGIN, AdminLog.RESULT_FAIL);
				return WebSite.getAdminTemplate("login");
			}
		} else {
			model.addAttribute("message", "对不起！您无权限登录此页面！");
//			adminLogMng.save(user, userName + " 无权限登录", AdminLog.TYPE_LOGIN, AdminLog.RESULT_FAIL);
			return WebSite.getAdminTemplate("login");
		}
		

	}
}
