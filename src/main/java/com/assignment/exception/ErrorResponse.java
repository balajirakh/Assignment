package com.assignment.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorResponse {
	private LocalDateTime timestamp;
	private String message;
	private int status;
	public ErrorResponse(LocalDateTime localDateTime, String message, int status) {
		
		this.timestamp = localDateTime;
		this.message = message;
		this.status = status;
	}
	

	
	
	
}
