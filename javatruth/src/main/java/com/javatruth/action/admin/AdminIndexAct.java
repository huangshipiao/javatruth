package com.javatruth.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
