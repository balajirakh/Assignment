package com.assignment.exception;

public class ApiResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiResponse(String message) {
		super();
		this.message = message;
	}
	
}
