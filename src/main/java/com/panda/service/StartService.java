package com.panda.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartService {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		CarService carService = (CarService)context.getBean("carService");
//		carService.update(new Car("benchi",3));
//		UserService userService = (UserService)context.getBean("userService");
//		userService.method1();
		Map<String, AbstractBeanDefinition> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, AbstractBeanDefinition.class, true, false);
		System.out.println(map.size());
		for(Entry<String, AbstractBeanDefinition> entry:map.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue().getBeanClassName());
		}
	}
}
