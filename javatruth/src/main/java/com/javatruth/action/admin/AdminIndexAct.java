package com.javatruth.action.admin;

import javax.annotation.Resource;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.ResponseUtils;
import com.common.utils.StringUtils;
import com.common.web.AjaxResult;
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
	@Autowired(required=false)
	private HttpSessionProvider session;
	
	@RequestMapping("index.html")
	public String adminIndex(HttpServletRequest request,Model model){		
		return WebSite.getAdminTemplate("index");
	}
	
	
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,Model model){		
		return WebSite.getAdminTemplate("login");
	}
	
	

	@RequestMapping(value = "login.do", method = {RequestMethod.POST})
	public void login(String username,String password,HttpServletRequest request,HttpServletResponse response,ModelMap model){		
		JSONObject jsonResult=new JSONObject();
		if(StringUtils.isBlank(username)){
			jsonResult.put("status", AjaxResult.STATUS_FAILED);
			jsonResult.put("msg", "用户名不能为空.");
			ResponseUtils.renderJson(response, jsonResult.toJSONString());	
			return;
		}
		if(StringUtils.isBlank(password)){			
			jsonResult.put("status", AjaxResult.STATUS_FAILED);
			jsonResult.put("msg", "密码不能为空.");
			ResponseUtils.renderJson(response, jsonResult.toJSONString());	
			return;
		}
		User user =userService.findBySysUserName(username);				
		if(user==null){			
			jsonResult.put("status",AjaxResult.STATUS_FAILED);
			jsonResult.put("msg", "用户名或密码错误.");
			ResponseUtils.renderJson(response, jsonResult.toJSONString());	
			return;
		}
		if (user.getUserType() == User.SYS_USER_TYPE&&user.getStatus()!=null&&user.getStatus().intValue()==User.STUTAS_NORMAL) {
			
			if (user != null && user.getUserPwd().equals(DigestUtils.md5Hex(password))) {
				/**
				 * 登录成功
				 */
				session.setAttribute(request, response, Constants.ADMIN_SESSION_KEY, user);
//				adminLogMng.save(user, userName + " 登录成功", AdminLog.TYPE_LOGIN, AdminLog.RESULT_SUCC);
				jsonResult.put("status", AjaxResult.STATUS_SUCCESS);
				jsonResult.put("msg", "登录成功");
				jsonResult.put("url", "index.html");
			} else {				
				jsonResult.put("status",  AjaxResult.STATUS_FAILED);
				jsonResult.put("msg", "用户名或密码错误.");
//				adminLogMng.save(user, userName + " 密码不正确", AdminLog.TYPE_LOGIN, AdminLog.RESULT_FAIL);				
			}
		} else {			
			jsonResult.put("status", AjaxResult.STATUS_FAILED);
			jsonResult.put("msg", "对不起！您无权限登录此页面！");
//			adminLogMng.save(user, userName + " 无权限登录", AdminLog.TYPE_LOGIN, AdminLog.RESULT_FAIL);
					
		}
		ResponseUtils.renderJson(response, jsonResult.toJSONString());	

	}
}
