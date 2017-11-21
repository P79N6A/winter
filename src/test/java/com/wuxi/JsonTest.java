package com.wuxi;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.codehaus.jackson.map.type.TypeFactory;
import org.junit.Test;

public class JsonTest {

	@Test
	public void type(){
		System.out.println(TypeFactory.type(Map.class));
	}
	
	@Test
	public void time(){
		Calendar cal = Calendar.getInstance();
		String fDate = "1501516800";
		System.out.println(Long.valueOf(fDate));
	    cal.setTimeInMillis(Long.valueOf(fDate)*1000);
		String firstLicensePlateYear = String.valueOf(cal.get(Calendar.YEAR));
		String firstLicensePlateMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
		System.out.println(firstLicensePlateYear);
		System.out.println(firstLicensePlateMonth);
	}
}
