package com.panda.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {

	@Transactional
	public String getUser(){
		return "user";
	}
	
}
