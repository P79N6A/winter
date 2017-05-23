package com.panda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserController {

	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@RequestMapping("/show")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("index");
		List<String> userList = new ArrayList<>();
		userList.add("李磊");
		userList.add("李白");
		
		view.addObject("users", userList);
		return view;
	}
}
