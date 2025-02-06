package com.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourcenotfoundexceptionhandler(ResourceNotFoundException ex) {

		String message = ex.getMessage();
		ErrorResponse api = new ErrorResponse(message, false, message);
		api.setMessage(message);
		api.setStatus(false);
		api.setNote("provide valid userid");

		return new ResponseEntity<ErrorResponse>(api, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(IllegalArgumentException1.class)
	public ResponseEntity<ApiResponse> IllegalArgumentException1(IllegalArgumentException1 ex) {

		String message = ex.getMessage();
         ApiResponse api = new ApiResponse(message);
         api.setMessage(message);

		return new ResponseEntity<ApiResponse>(api, HttpStatus.BAD_REQUEST);

	}

}
