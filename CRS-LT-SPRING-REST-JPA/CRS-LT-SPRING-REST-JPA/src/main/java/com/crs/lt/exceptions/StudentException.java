package com.crs.lt.exceptions;

public class StudentException extends Exception {
	private static final long serialVersionUID = -2736163557473481863L;
	private String userId;
	private String message;
	
	
	public StudentException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
