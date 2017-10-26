package com.panda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.panda.bean.Car;
import com.panda.dao.CarMapper;
import com.panda.dao.UserMapper;

@Service("carService")
public class CarService {
	private static final Logger logger = LoggerFactory.getLogger(CarService.class);
	
	@Autowired
	CarMapper carMapper;
	
	
	public void add(Car car){
		carMapper.add(car);
	}
	
	@Transactional
	public void update(Car car){
		System.out.println(carMapper.update(car));
		String price = carMapper.selectPrice(car.getName());
		logger.info("price:{}",price);
		
	}
}
