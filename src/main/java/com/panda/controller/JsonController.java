package com.panda.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.bean.User;
import com.panda.service.UserService;

@Controller
@RequestMapping("json")
public class JsonController {

	private static final Logger logger = LoggerFactory.getLogger(JsonController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/hello")
	@ResponseBody
	public Object  getData(@RequestBody String body,@RequestHeader(name="Content-type") String contentType,
			@RequestParam(name="phone") String phone,@RequestParam String name){
		logger.info("body: " + body);
		logger.info("content-type: " + contentType);
		Map<String, String> map = new HashMap<String,String>();
		map.put("name", "zz");
		map.put("sex", "男");
		logger.info(phone + " " + name);
		User user = new User();
		user.setName("张三");
		user.setAge(10);
		
		logger.info(userService.getUser());
		return user;
	}
	
	/*
	 * http://localhost:8080/json/hi?phone=1&names=a&names=b
	 * 参数注入数组
	 */
	@RequestMapping(value = "/hi")
	@ResponseBody
	public Object  getHi(@RequestParam(name="phone") String phone,
			@RequestParam(name="names") String[] names,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		//map.put("names", request.getParameterValues("names"));
		map.put("names", names);
		
		return map;
	}
}


