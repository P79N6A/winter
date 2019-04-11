package com.wuxi;

import java.util.ArrayList;
import java.util.List;

import com.panda.bean.Car;

public class CollectTest {

    public static void main(String[] args) {
        List<Car> l1 = new ArrayList<>();
        l1.add(new Car("aa", 1));
        l1.add(new Car("bb", 2));
        //浅拷贝
        List<Car> l2 = new ArrayList<>(l1);
//		for(Car car : l2){
//			car.setPrice(3);
//		}
        System.out.println(l1);
        System.out.println(l2);
    }
}
