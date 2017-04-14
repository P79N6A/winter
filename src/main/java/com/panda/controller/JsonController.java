package com.panda.controller;

import java.util.HashMap;
import java.util.Map;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.bean.User;

@Controller
@RequestMapping("json")
public class JsonController {

	private static final Logger logger = LoggerFactory.getLogger(JsonController.class);
	
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
		return user;
	}
}


