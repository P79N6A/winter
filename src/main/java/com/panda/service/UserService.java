package com.panda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.bean.Car;
import com.panda.bean.User;
import com.panda.dao.UserMapper;

@Service("userService")
public class UserService {

	@Autowired
	CarService carService;
	
	@Autowired
	UserMapper userMapper;
	
	@Transactional
	public String getUser(){
		//updateAge(new User("李强",1));
		method1();
		return "user";
	}
	
	@Transactional
	public void updateAge(User user){
		userMapper.updateAge(user);
		carService.update(new Car("benchi",1));
		throw new RuntimeException();
	}
	
	//开启事务:1、基于接口代理：只有public方法才能，其他都不行。2、基于cglib代理，public,protected可以，static,final,private都不行。
	//但是代码仍可以运行在事务环境中，只要其他事务方法调用此方法将事务传播到此。
	@Transactional
	public  void method1() {
		System.out.println("method1");
		carService.update(new Car("benchi",1));
	}
}
