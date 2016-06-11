package com.common.web.session;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session提供者
 * 
 * @author huangshipiao
 * 
 */
public interface SessionProvider{
    public static final String JSESSION_COOKIE = "JSESSIONID";
    public static final String JSESSION_URL = "jsessionid";

    public Serializable getAttribute(HttpServletRequest request, String name);

    public void setAttribute(HttpServletRequest request,
			HttpServletResponse response, String name, Serializable value);

    public String getSessionId(HttpServletRequest request,
			HttpServletResponse response);

    public void logout(HttpServletRequest request, HttpServletResponse response);


}
