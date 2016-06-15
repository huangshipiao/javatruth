package com.javatruth.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.web.WebSite;
import com.javatruth.service.IUserService;

@Controller
@RequestMapping("/admin/*")
public class AdminIndexAct {
	
	@Resource
	private IUserService userService;
	
	
	@RequestMapping("index.html")
	public String adminIndex(HttpServletRequest request,Model model){		
		return WebSite.getAdminTemplate("index");
	}
	
	
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,Model model){		
		return WebSite.getAdminTemplate("login");
	}
	
	
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request,Model model){		
		
		return WebSite.getAdminTemplate("login");
	}
}
