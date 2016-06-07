package com.javatruth.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatruth.service.IUserService;

@Controller
public class IndexAct {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("/index.html")
	public String toIndex(HttpServletRequest request,Model model){		
		return "index";
	}
	
}
