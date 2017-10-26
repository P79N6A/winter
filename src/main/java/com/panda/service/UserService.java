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
	
	public void updateAge(User user){
		userMapper.updateAge(user);
		carService.update(new Car("benchi",1));
		throw new RuntimeException();
	}
	
	//只有public方法才能开启事务，static,final,private不行。
	//但是代码仍可以运行在事务环境中，只要其他事务方法调用此方法将事务传播到此。
	//@Transactional
	public final void method1() {
		System.out.println("method1");
		carService.update(new Car("benchi",1));
	}
}
