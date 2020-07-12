package com.prac.rest.webservice.RestfulservicesDemo.beans;

public class Name {
	private String fisrtName;
	private String LastName;
	
	public Name(String fisrtName, String lastName) {
		super();
		this.fisrtName = fisrtName;
		LastName = lastName;
	}
	public String getFisrtName() {
		return fisrtName;
	}
	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
}
