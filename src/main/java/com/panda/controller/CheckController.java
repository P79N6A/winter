package com.panda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/check")
public class CheckController {

	@RequestMapping("/health")
	public ModelAndView health(){
		ModelAndView view = new ModelAndView("success");
		return view;
	}
}
