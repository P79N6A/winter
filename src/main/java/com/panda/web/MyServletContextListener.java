package com.panda.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.panda.service.websocket.WebSocketServer;

public class MyServletContextListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);
	
	private ServletContext servletContext;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		servletContext = sce.getServletContext();
		servletContext.setAttribute("name", "souche");
		logger.info(servletContext.getContextPath());
		logger.info(servletContext.getRealPath("/"));
		//new WebSocketServer(10000).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		servletContext = null;
	}

}
