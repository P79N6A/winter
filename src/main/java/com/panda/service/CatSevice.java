package com.panda.service;

public class CatSevice {
	
	public static void main(String[] args) {
		Body body = new Body();
		body.setName("xx");
		Body dBody = null;
		try {
			dBody = (Body) body.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(body == dBody);
		System.out.println(body.getName() == dBody.getName());
		
	}
	
}


class Body implements Cloneable{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
	
	
	
}