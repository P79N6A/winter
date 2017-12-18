package com.panda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@RequestMapping("/produce")
	@ResponseBody
	public String produce(@RequestParam String code,@RequestParam String price){
		
		kafkaTemplate.sendDefault(code+"|"+price);
		return "success";
	}
}
