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
		updateAge(new User("李强",1));
		return "user";
	}
	
	@Transactional
	public void updateAge(User user){
		userMapper.updateAge(user);
		carService.update(new Car("benchi",1));
		throw new RuntimeException();
	}
	
}
