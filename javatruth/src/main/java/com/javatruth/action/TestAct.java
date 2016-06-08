package com.javatruth.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.config.Global;
import com.common.persistence.page.Page;
import com.common.utils.DateTimeUtils;
import com.common.utils.ResponseUtils;
import com.common.web.AjaxResult;
import com.common.web.WebSite;
import com.javatruth.entity.User;
import com.javatruth.service.IUserService;

@Controller
@RequestMapping("/test")
public class TestAct {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("/test.html")
	public String toTest(HttpServletRequest request,HttpServletResponse response,Model model){
		
		User user =new User();
		/*try{
		   user = this.userService.selectByPrimaryKey(userId);
		   System.out.println("user:"+user);
		}catch(Exception e){
			e.printStackTrace();
			
		}*/
//		user.setUserName("13760697997");
		Page<User> page=userService.fingPage(new Page<User>(request,response,5), user);
		System.out.println(page);
		System.out.println(page.getList());
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		model.addAttribute("productName", Global.getConfig("productName"));
		return WebSite.getFrontTemplate("test/test");
	}
	@RequestMapping("/add.do")
	public void addUser(Integer num,HttpServletRequest request,HttpServletResponse response, Model model){
		AjaxResult<User> ajaxResult= new AjaxResult<User>();
		User user =null;
		if(num==null){
			ajaxResult.setStatus(AjaxResult.STATUS_FAILED);
	         ajaxResult.setMsg("用户数量不能为空");
		}
		else if(num<1){
			ajaxResult.setStatus(AjaxResult.STATUS_FAILED);
	         ajaxResult.setMsg("用户数量必须大于0");
		}
		else{
			for (int i = 0; i < num; i++) {
				user =new User();
				user.setCreateTime(DateTimeUtils.getCurrentDate());
				user.setLastIp("127.0.0.1");
				user.setLastLogin(DateTimeUtils.getCurrentDate());
				user.setNickName("piao");
				user.setSex(1);
				user.setMobile("13760697997");
				user.setStatus(0);
				user.setLoginNum(i);
				user.setUserPwd("123456");
				user.setUserType(0);
				user.setHeadImg("");
				user.setUserName("1376069799"+i);
				user.setUpateTime(DateTimeUtils.getCurrentDate());
				System.out.println(user);
				userService.insert(user);
			}
			ajaxResult.setStatus(AjaxResult.STATUS_SUCCESS);
			ajaxResult.setMsg("用户新建成功");
		}
		ResponseUtils.renderJson(response, new JSONObject(ajaxResult).toString());
	}
	
}
