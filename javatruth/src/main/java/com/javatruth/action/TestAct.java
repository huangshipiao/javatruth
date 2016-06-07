package com.javatruth.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.config.Global;
import com.common.web.WebSite;
import com.javatruth.entity.User;
import com.javatruth.service.IUserService;

@Controller
@RequestMapping("/test")
public class TestAct {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("/test.html")
	public String toTest(HttpServletRequest request,Model model){
		
		User user = user=new User();
		/*try{
		   user = this.userService.selectByPrimaryKey(userId);
		   System.out.println("user:"+user);
		}catch(Exception e){
			e.printStackTrace();
			
		}*/
		user.setUserName("13760697997");
		model.addAttribute("user", user);
		model.addAttribute("productName", Global.getConfig("productName"));
		return WebSite.getFrontTemplate("test/test");
	}
	
}
