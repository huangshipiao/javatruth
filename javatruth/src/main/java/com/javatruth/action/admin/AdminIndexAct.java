package com.javatruth.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.utils.RequestUtils;
import com.common.utils.StringUtils;
import com.common.web.WebSite;
import com.javatruth.service.IUserService;

@Controller
@RequestMapping("/admin/*")
public class AdminIndexAct {
	
	@Resource
	private IUserService userService;
	
	
	@RequestMapping("index.html")
	public String adminIndex(HttpServletRequest request,ModelMap model){		
		return WebSite.getAdminTemplate("index");
	}
	
	
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,ModelMap model){		
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
//		userService.findByUserName(username);
		model.addAttribute("message", "登录成功");
		return WebSite.getAdminTemplate("login");
	}
	
	
}
