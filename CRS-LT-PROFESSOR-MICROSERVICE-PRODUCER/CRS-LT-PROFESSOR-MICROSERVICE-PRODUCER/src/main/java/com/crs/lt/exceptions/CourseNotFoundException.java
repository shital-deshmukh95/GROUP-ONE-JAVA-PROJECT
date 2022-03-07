package com.crs.lt.exceptions;

/**
 * 
 * @author user215
 *
 */
public class CourseNotFoundException extends Exception{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String courseCode;
	
     public  String message;

     
	public CourseNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CourseNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}


	public CourseNotFoundException(String courseCode, String message) {
		super();
		this.courseCode = courseCode;
		this.message = message;
	}


	public String getCourseCode() {
		return courseCode;
	}


	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
     
	
}

	
