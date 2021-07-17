package com.salaryspringmvc.models;

public class Order {
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Order(Person person) {
		super();
		this.person = person;
	}
	
}
