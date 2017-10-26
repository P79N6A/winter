package com.panda.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.panda.bean.Car;
import com.panda.bean.User;

public class StartService {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		CarService carService = (CarService)context.getBean("carService");
//		carService.update(new Car("benchi",3));
		UserService userService = (UserService)context.getBean("userService");
		userService.method1();
		
	}
}
