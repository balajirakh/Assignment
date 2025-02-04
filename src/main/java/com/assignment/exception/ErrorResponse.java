package com.assignment.exception;


public class ErrorResponse {

	private String message;
	private boolean status;
	private String note;
	public ErrorResponse(String message, boolean status, String note) {
		super();
		this.message = message;
		this.status = status;
		this.note = note;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
