package com.prac.rest.webservice.RestfulservicesDemo.exception;

import java.util.Date;

public class ExceptionResponse {
	private Integer statusCode;
	private Date timestump;
	private String message;
	private String description;
	
	public ExceptionResponse(Integer statusCode, Date timestump, String message, String description) {
		super();
		this.statusCode = statusCode;
		this.timestump = timestump;
		this.message = message;
		this.description = description;
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public Date getTimestump() {
		return timestump;
	}
	public void setTimestump(Date timestump) {
		this.timestump = timestump;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
