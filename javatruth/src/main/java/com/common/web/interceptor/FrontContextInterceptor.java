package com.common.web.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.config.Global;
import com.common.web.Constants;
import com.common.web.session.SessionProvider;
import com.common.web.threadvariable.MemberThread;
import com.javatruth.entity.User;

public class FrontContextInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {
		User user=(User) session.getAttribute(request, Constants.MEMBER_SESSION_KEY);
		// 用户信息存在
		if (user!=null) {
			// 将用户信息放入ThreadLocal
			MemberThread.set(user);			
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		//获取前台用户登录信息
		User user=MemberThread.get();
		//请求转发
		if (user!=null && mav != null && mav.getModelMap() != null
				&& mav.getViewName() != null
				&& !mav.getViewName().startsWith("redirect:")) {
			mav.getModelMap().addAttribute(Constants.AUTH_KEY,
					user.getUserName()+user.getId());
		}else{
			 response.sendRedirect(loginUrl);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		MemberThread.remove();
	}
	@Autowired
	private SessionProvider session;
	
	private String loginUrl=Global.getAdminLoginUrl();
}
