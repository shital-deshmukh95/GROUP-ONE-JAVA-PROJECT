package com.crs.lt.exceptions;

public class CourseExistsAlreadyException extends Exception {
private String courseCode;
	
	public CourseExistsAlreadyException(String courseCode) {
		this.courseCode = courseCode;
	}
	

	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course: " + courseCode + " already exists in catalog.";
	}
}
