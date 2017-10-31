package com.panda.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.panda.bean.User;
import com.panda.dao.UserMapper;

@Controller
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping("/show")
	public ModelAndView show(){
		ModelAndView view = new ModelAndView("show");
		List<String> userList = new ArrayList<>();
		userList.add("李磊");
		userList.add("李白");
		view.addObject("users", userList);
		return view;
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST,params={"name","age"})
	public ModelAndView addUser(User user,@CookieValue("JSESSIONID")String cookie,@RequestBody String body,@RequestHeader Map<String, String> header){
		logger.info(header.toString());
		logger.info(cookie);
		logger.info(body);
		
		logger.info(user.getName());
		ModelAndView view = new ModelAndView("createSuccess");
		view.addObject("user",user);
		return view;
	}
	
	@RequestMapping(value="/updateUser",produces={"application/toString","application/json"})
	@ResponseBody
	public User updateUser(User user){
		return user;
	}
	
	@RequestMapping("register")
	public String register(){
		return "addUser";
	}
	
	@RequestMapping("update")
	public String update(){
		return "updateUser";
	}
}
