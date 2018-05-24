package com.panda.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class UserController implements ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {

	}
	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Autowired
	UserMapper userMapper;
	
	ApplicationContext ac;
	
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
	public ModelAndView addUser(User user,Model model,HttpServletRequest request, @ModelAttribute("name") String name,BindingResult result,
			@CookieValue("JSESSIONID")String cookie,@RequestBody String body,@RequestHeader Map<String, String> header){
		logger.info(header.toString());
		logger.info(cookie);
		logger.info(body);
//		if(!StringUtils.isEmpty(name)){
//			throw new NullPointerException();
//		}
		request.setAttribute("nn", "aa");
		//getUser会在请求处理前执行，并将值赋给入参，然后再根据http请求信息对user覆盖，得到整合版的user
		logger.info(user.getName());
		logger.info(model.asMap().get("user").toString());
		logger.info(name);
//		ModelAndView view = new ModelAndView("redirect:/user/register");
//		ModelAndView view = new ModelAndView("forward:/user/register");		
		ModelAndView view = new ModelAndView("createSuccess");
//		view.addObject(user);
		return view;
	}
	
	@ModelAttribute("user")
	public User getUser(){
		logger.info("bb");
		User user = new User();
		user.setName("bb");
		user.setAge(3);
		return user;
	}
	
	@RequestMapping(value="/updateUser",produces={"application/toString","application/json","application/xml"})
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
	
	@RequestMapping("setting")
	public String setting(){
		return "setting";
	}
	
	@RequestMapping("/property")
	@ResponseBody
	public byte[] property() throws IOException{
		Resource resource = ac.getResource("img/success.jpg");
		return FileCopyUtils.copyToByteArray(resource.getInputStream());
	}
	
	@RequestMapping("/down")
	public void down(HttpServletResponse response) throws IOException{
		//Resource resource = ac.getResource("img/success.jpg");
		Resource resource = ac.getResource("file:/Users/dasouche/Downloads/Spring源码深度解析.pdf");
		//attachment --- 作为附件下载
	    //inline --- 在线打开
		//response.setHeader("Content-Disposition", "attachment; filename=" + new String(resource.getFilename().getBytes("utf-8"),"ISO-8859-1"));
		
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(FileCopyUtils.copyToByteArray(resource.getInputStream()));
		outputStream.flush();
		outputStream.close();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
	}
}
