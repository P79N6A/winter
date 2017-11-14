package com.panda.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

import com.panda.bean.User;

public class UrlController extends AbstractController implements LastModified {

	private long lastModified = 0;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView view = new ModelAndView("show");
		User user = new User();
		user.setName("lisi");
		user.setAge(4);
		view.addObject(user);
		return view;
	}

	@Override
	public long getLastModified(HttpServletRequest request) {
		if(lastModified == 0){
			lastModified = System.currentTimeMillis();
		}
		return lastModified;
	}
	
	

}
