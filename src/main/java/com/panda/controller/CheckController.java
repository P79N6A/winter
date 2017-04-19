package com.panda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/check")
public class CheckController {

	private static final Logger logger = LoggerFactory.getLogger(CheckController.class);
	
	@RequestMapping("/health")
	public ModelAndView health(@RequestParam String text){
		ModelAndView view = new ModelAndView("success");
		logger.info(text);
		view.addObject("text", text);
		return view;
	}
	
	@RequestMapping("hello")
	public void hello(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		logger.info("1");
		PrintWriter write = resp.getWriter();
		logger.info("2");
		 Date today = new Date();
		 write.println("<html>"
				 +"<meta http-equiv=\"charset\" content=\"utf-8\">"
				 +"<body><p>" + today +"</p>" 
				 +"<p>hello</p>"
				 + "</body></html>");
		 logger.info("3");
		 write.flush();
		 logger.info("4");
		 write.close();
		 logger.info("5");
	}
}
