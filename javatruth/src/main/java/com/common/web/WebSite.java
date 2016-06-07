package com.common.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.common.utils.StringUtils;
import com.common.utils.WebErrors;

/**
 * 网站模板工具类
 * @author huangshipiao
 *
 */
public class WebSite {
	public static final String PATH_SP = "/";// /
	public static final String WEBINF_BASE = "WEB-INF";// WEB-INF
	public static final String PAGES_BASE = "pages";//
	public static final String RES_BASE = "res";// res
	public static final String ADMIN_BASE = "admin";// admin
	public static final String FRONT_BASE = "front";// front
	public static final String MOBILE_BASE = "mobile";// mobile
	public static final String WEBAPP_BASE = "webapp";// webapp
	public static final String UPLOAD_PATH = "upload";// upload
	public static final String TPL_SUFFIX = ".html";// .html
	
	/**
	 * 指定/WEB-INF/pages/admin/文件夹路径，页面使用.html后缀 参数path不能带页面名后缀
	 * 示例：path=team/index 方法返回/WEB-INF/pages/admin/team/index.html
	 * 
	 * @param path
	 *            页面路径
	 * @return 模板全路径
	 */
	public static String getAdminTemplate(String path) {
		if (StringUtils.isBlank(path)) {
			return null;
		}
		if(path.contains("redirect:")){
			return path;
		}
		StringBuilder sbuider = new StringBuilder();
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(ADMIN_BASE);
		if (path.startsWith("/")) {
			sbuider.append(path.substring(1)).append(TPL_SUFFIX);
		} else {
			sbuider.append(path).append(TPL_SUFFIX);
		}
		return sbuider.toString();
	}
	
	/**
	 * 指定/WEB-INF/pages/front/文件夹路径，页面使用.html后缀 参数path不能带页面名后缀
	 * 示例：path=team/index 方法返回/WEB-INF/pages/front/team/index.html
	 * 
	 * @param path
	 *            页面路径
	 * @return 模板全路径
	 */
	public static String getFrontTemplate(String path) {
		if (StringUtils.isBlank(path)) {
			return null;
		}
		if(path.contains("redirect:")){
			return path;
		}
		StringBuilder sbuider = new StringBuilder("/");
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(FRONT_BASE);
		if (path.startsWith("/")) {
			sbuider.append(path).append(TPL_SUFFIX);
		} else {
			sbuider.append("/").append(path).append(TPL_SUFFIX);
		}
		return sbuider.toString();
	}
	/**
	 * 指定/WEB-INF/pages/webapp/文件夹路径，页面使用.html后缀 参数path不能带页面名后缀
	 * 示例：path=team/index 方法返回/WEB-INF/pages/webapp/team/index.html
	 * 
	 * @param path
	 *            页面路径
	 * @return 模板全路径
	 */
	public static String getWebAppTemplate(String path) {
		if (StringUtils.isBlank(path)) {
			return null;
		}
		if(path.contains("redirect:")){
			return path;
		}
		StringBuilder sbuider = new StringBuilder();
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(WEBAPP_BASE);
		if (path.startsWith("/")) {
			sbuider.append(path.substring(1)).append(TPL_SUFFIX);
		} else {
			sbuider.append(path).append(TPL_SUFFIX);
		}
		return sbuider.toString();
	}
	
	
	
	/**
	 * 指定/WEB-INF/pages/mobile/文件夹路径，页面使用.html后缀 参数path不能带页面名后缀
	 * 示例：path=team/index 方法返回/WEB-INF/pages/webapp/team/index.html
	 * 
	 * @param path
	 *            页面路径
	 * @return 模板全路径
	 */
	public static String getMobileTemplate(String path) {
		if (StringUtils.isBlank(path)) {
			return null;
		}
		if(path.contains("redirect:")){
			return path;
		}
		StringBuilder sbuider = new StringBuilder();
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(MOBILE_BASE);
		if (path.startsWith("/")) {
			sbuider.append(path.substring(1)).append(TPL_SUFFIX);
		} else {
			sbuider.append(path).append(TPL_SUFFIX);
		}
		return sbuider.toString();
	}
	
	/**
	 * 指定/WEB-INF/pages/front/文件夹路径
	 * 
	 * @param path
	 *            页面路径
	 * @return 模板全路径
	 */
    public static String getTemplateRel(){
        return getTemplateRel(null);
    }
    
	/**
	 * 指定/WEB-INF/pages/front/文件夹路径，
	 * 示例：path=team/index 方法返回/WEB-INF/pages/front/team/index
	 * 
	 * @param path
	 *            文件夹路径
	 * @return 模板全路径
	 */
    public static String getTemplateRel(String s){
		StringBuilder sbuider = new StringBuilder("/");
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(FRONT_BASE);
        if(!StringUtils.isBlank(s)){
            if(!s.startsWith("/")){
            	sbuider.append("/");
            }
            sbuider.append(s);
        }
        return sbuider.toString();
    }

    
	/**
	 * 指定/WEB-INF/pages/webapp/文件夹路径，
	 * 示例：path=team/index 方法返回/WEB-INF/pages/webapp/team/index
	 * 
	 * @param path
	 *            文件夹路径
	 * @return 模板全路径
	 */
    public static String getWebAppTemplateRel(String s){
		StringBuilder sbuider = new StringBuilder("/");
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(WEBAPP_BASE);
        if(!StringUtils.isBlank(s)){
            if(!s.startsWith("/")){
            	sbuider.append("/");
            }
            sbuider.append(s);
        }
        return sbuider.toString();
    }
	/**
	 * 指定/WEB-INF/pages/front/文件夹路径，页面使用自定义的后缀 \n 示例：path=team/index
	 * 方法返回/WEB-INF/pages/front/team/index.html
	 * 
	 * @param path
	 *            页面路径
	 * @param tplsuffix
	 *            页面后缀
	 * @return 模板全路径
	 */
	public static String getFrontTemplate(String path, String tplsuffix) {
		if (StringUtils.isBlank(path)) {
			return null;
		}
		StringBuilder sbuider = new StringBuilder("/");
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(FRONT_BASE);
		if (path.startsWith("/")) {

		} else {
			sbuider.append("/").append(path).append(tplsuffix);
		}
		return sbuider.toString();
	}
	
	/**
	 * 把参数转存到model里
	 * 
	 * @param relPath
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	public static void setParameters(HttpServletRequest request, ModelMap model) {
		Enumeration<String> parameterNames = request.getParameterNames();
		if (parameterNames != null) {
			for (; parameterNames.hasMoreElements();) {
				String name = parameterNames.nextElement();
				model.addAttribute(name, request.getParameter(name));
			}
		}
	}
	
	
	
	
	/**
	 * 显示前台错误页
	 * @param weberrors
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	public static String showFrontError(WebErrors weberrors,ModelMap model, HttpServletRequest request) {
		weberrors.toModel(model);
		return getFrontTemplate("common/error_message");
	}
	
	/**
	 * 显示前台错误页
	 * @param weberrors
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	public static String showMobileError(WebErrors weberrors,ModelMap model, HttpServletRequest request) {
		weberrors.toModel(model);
		return getMobileTemplate("common/error_message");
	}

	/**
	 * 显示后台错误页
	 * @param weberrors
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	public static String showAdminError(WebErrors weberrors,ModelMap model, HttpServletRequest request) {
		weberrors.toModel(model);
		return getMobileTemplate("common/error_message");
	}
	
	
}
