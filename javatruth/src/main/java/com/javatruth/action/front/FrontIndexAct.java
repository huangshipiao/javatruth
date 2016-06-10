package com.javatruth.action.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.web.WebSite;
import com.javatruth.service.IUserService;

@Controller
@RequestMapping("/front/*")
public class FrontIndexAct {
	
	@Resource
	private IUserService userService;
	
	
	@RequestMapping("index.html")
	public String frontIndex(HttpServletRequest request,Model model){		
		return WebSite.getFrontTemplate("index");
	}
}
