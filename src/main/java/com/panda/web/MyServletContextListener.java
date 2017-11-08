package com.panda.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	private ServletContext servletContext;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		servletContext = sce.getServletContext();
		servletContext.setAttribute("name", "souche");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		servletContext = null;
	}

}
