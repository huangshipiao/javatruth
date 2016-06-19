package com.javatruth.action.front;

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

import com.alibaba.fastjson.JSONObject;
import com.common.utils.CodeBuilder;
import com.common.utils.EmailTlsHandler;
import com.common.utils.PropertyUtil;
import com.common.utils.ResponseUtils;
import com.common.utils.StringUtils;
import com.common.web.AjaxResult;
import com.common.web.Constants;
import com.common.web.WebSite;
import com.common.web.session.HttpSessionProvider;
import com.javatruth.entity.User;
import com.javatruth.service.IUserService;

@Controller
@RequestMapping("/front/*")
public class FrontIndexAct {
	
	@Resource
	private IUserService userService;
	@Autowired(required=false)
	private HttpSessionProvider session;
	@Autowired
	private EmailTlsHandler emailTlsHandler;
	@RequestMapping("index.html")
	public String frontIndex(HttpServletRequest request,Model model){		
		return WebSite.getFrontTemplate("index");
	}
	
	
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,Model model){		
		return WebSite.getFrontTemplate("login");
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
		User user =userService.findByUserName(username);				
		if(user==null){			
			jsonResult.put("status",AjaxResult.STATUS_FAILED);
			jsonResult.put("msg", "用户名或密码错误.");
			ResponseUtils.renderJson(response, jsonResult.toJSONString());	
			return;
		}
		if (user.getUserType() == User.FRONT_USER_TYPE&&user.getStatus()!=null&&user.getStatus().intValue()==User.STUTAS_NORMAL) {
			
			if (user != null && user.getUserPwd().equals(DigestUtils.md5Hex(password))) {
				/**
				 * 登录成功
				 */
				session.setAttribute(request, response, Constants.ADMIN_SESSION_KEY, user);
				jsonResult.put("status", AjaxResult.STATUS_SUCCESS);
				jsonResult.put("msg", "登录成功");
				jsonResult.put("url", "index.html");
			} else {				
				jsonResult.put("status",  AjaxResult.STATUS_FAILED);
				jsonResult.put("msg", "用户名或密码错误.");		
				
			}
		} else {			
			jsonResult.put("status", AjaxResult.STATUS_FAILED);
			jsonResult.put("msg", "对不起！您的账号已被锁定！");					
		}
		

		
		Thread t = new Thread(new Runnable() {
			public void run() {				
				emailTlsHandler.sendMail("test", "用户名或密码错误.", "517557384@qq.com", "");				
			}
		});
		t.start();
		ResponseUtils.renderJson(response, jsonResult.toJSONString());	
	}
	
	@RequestMapping(value = "sendEmailCode.do", method = {RequestMethod.POST})
	public void sendEmailCode(String email,HttpServletRequest request,HttpServletResponse response,ModelMap model){		
		JSONObject jsonResult=new JSONObject();
		if(StringUtils.isBlank(email)){
			jsonResult.put("status", AjaxResult.STATUS_FAILED);
			jsonResult.put("msg", "Email不能为空.");
			ResponseUtils.renderJson(response, jsonResult.toJSONString());	
			return;
		}
		try {
			String content = PropertyUtil.getMessagesProperty("mail.register.content");
			content.replace("${code}", CodeBuilder.sixNO());
			String subject = PropertyUtil.getMessagesProperty("mail.register.subject");
			emailTlsHandler.sendMail(subject, content, email, "");
			jsonResult.put("status", AjaxResult.STATUS_SUCCESS);
			jsonResult.put("msg", "注册码发送成功，请注意查收.");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", AjaxResult.STATUS_FAILED);
			jsonResult.put("msg", "注册码发送失败，请稍后再试.");
		}
		ResponseUtils.renderJson(response, jsonResult.toJSONString());	
	}
	
	
	
}
