package com.salaryspringmvc.models;

public class Person {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person(String name) {
		super();
		this.name = name;
	}
	
	public void init() {
		System.out.println("Person bean is initialized!");
	}
	public void destroy() {
		System.out.println("Person bean is destroyed!");
	}
}
