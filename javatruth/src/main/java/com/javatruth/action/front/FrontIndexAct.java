package com.javatruth.action.front;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
	
	
	/**
	 * 图片验证码
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/register/random.do", method = RequestMethod.GET)
	public void random(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			int width = 50;
			int height = 18;
			// 取得一个4位随机字母数字字符串
			String s = RandomStringUtils.random(4, true, true);

			// 保存入session,用于与用户的输入进行比较.
			// 注意比较完之后清除session.
			HttpSession session = request.getSession(true);
			session.setAttribute("validateCode", s);

			response.setContentType("images/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream out = response.getOutputStream();
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			// 设定背景色
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			// 设定字体
			Font mFont = new Font("Times New Roman", Font.BOLD, 18);// 设置字体
			g.setFont(mFont);

			// 画边框
			// g.setColor(Color.BLACK);
			// g.drawRect(0, 0, width - 1, height - 1);

			// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
			g.setColor(getRandColor(160, 200));
			// 生成随机类
			Random random = new Random();
			for (int i = 0; i < 155; i++) {
				int x2 = random.nextInt(width);
				int y2 = random.nextInt(height);
				int x3 = random.nextInt(12);
				int y3 = random.nextInt(12);
				g.drawLine(x2, y2, x2 + x3, y2 + y3);
			}

			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(s, 2, 16);

			// 图象生效
			g.dispose();
			// 输出图象到页面
			ImageIO.write((BufferedImage) image, "JPEG", out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
