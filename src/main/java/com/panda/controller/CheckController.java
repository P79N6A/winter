package com.panda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/check")
public class CheckController {

	@RequestMapping("/health")
	public ModelAndView health(@RequestParam String text){
		ModelAndView view = new ModelAndView("success");
		System.out.println(text);
		return view;
	}
}
