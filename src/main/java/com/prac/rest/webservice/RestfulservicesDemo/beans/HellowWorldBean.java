package com.prac.rest.webservice.restfulservicesdemo.beans;

import org.springframework.stereotype.Component;

@Component
public class HellowWorldBean {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
