package com.panda.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("json")
public class JsonController {

	private static final Logger logger = LoggerFactory.getLogger(JsonController.class);
	
	@RequestMapping(value = "/hello")
	@ResponseBody
	public Object  getData(){
		MDC.put("name", "zhanghui");
		Map<String, String> map = new HashMap<String,String>();
		map.put("name", "zz");
		map.put("sex", "男");
		logger.info("男");
		
		try {
			int a = Integer.valueOf(map.get("sex"));
		} catch (Exception e) {
			logger.error("转换错误 ",e);
		}
		
		return map;
	}
}
