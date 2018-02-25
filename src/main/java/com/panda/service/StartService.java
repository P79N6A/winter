package com.panda.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.panda.bean.Car;

public class StartService {
	
	public static void main(String[] args) {
//		try {
//			System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//context.start();
		CarService carService = (CarService)context.getBean("carService");
		carService.update(new Car("benchi",3));
//		UserService userService = (UserService)context.getBean("userService");
//		userService.method1();
//		Map<String, AbstractBeanDefinition> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, AbstractBeanDefinition.class, true, false);
//		System.out.println(map.size());
//		for(Entry<String, AbstractBeanDefinition> entry:map.entrySet()){
//			System.out.println(entry.getKey());
//			System.out.println(entry.getValue().getBeanClassName());
//		}
	}
}
