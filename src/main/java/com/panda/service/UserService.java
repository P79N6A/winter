package com.panda.service;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

	public String getUser(){
		return "user";
	}
}
