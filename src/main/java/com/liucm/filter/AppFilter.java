package com.liucm.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "AppFilter", urlPatterns = "/*")
public class AppFilter implements Filter{
	
	//排除一些拦截的请求
	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/","/css/auth.css",
            		"/img/kodinger.jpg",
            		"/css/kefu.css",
            		"/js/auth.js",
            		"/js/jquery.min.js",
            		"/UserController/login")));
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getRequestURI();
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        if(!allowedPath) {
        	HttpSession session = request.getSession();
        	if(session != null && session.getAttribute("user") != null) {

            	//获取父url--如果不是直接输入的话就是先前的访问过来的页面，要是用户输入了，这个父url是不存在的 
        		String conString = request.getHeader("REFERER");

            	//当前请求url，去掉几个可以直接访问的页面  
            	String servletPath = request.getServletPath();

            	//去掉几个特殊访问，拦截器拦截所有请求，重定向到专属错误页面。
            	if(conString !=null|| (!servletPath.contains("videos") && !servletPath.contains("imahes"))) {
                	chain.doFilter(request, response);
            	}else {
            		response.sendRedirect("/error/404.html");
            	}
        		
        	}else {
        		response.sendRedirect("/");		
        	}
        } else {
        	chain.doFilter(request, response);
        }
	}
	
}
