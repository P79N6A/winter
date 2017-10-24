package com.panda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.bean.Car;
import com.panda.dao.CarMapper;

@Service("carService")
public class CarService {

	@Autowired
	CarMapper carMapper;
	
	
	public void add(Car car){
		carMapper.add(car);
	}
	
	@Transactional(readOnly=true)
	public void update(Car car){
		int a = carMapper.update(car);
		System.out.println(a);
	}
}
