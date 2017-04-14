package com.panda.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		String path = request.getServletPath();
		logger.info("servlet path: " + path);
		
		return true;
    }
}
