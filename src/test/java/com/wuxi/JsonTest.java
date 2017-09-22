package com.wuxi;

import java.util.Map;

import org.codehaus.jackson.map.type.TypeFactory;
import org.junit.Test;

public class JsonTest {

	@Test
	public void type(){
		System.out.println(TypeFactory.type(Map.class));
	}
}
