package com.crs.lt.exceptions;
/**
 * 
 * @author user215
 *
 */
public class CourseNotDeletedException extends Exception {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String courseCode;
	
	public CourseNotDeletedException(String courseCode)
	{	
		this.courseCode = courseCode;
	}

	
	public String getCourseCode()
	{
		return courseCode;
	}
	
	@Override
	public String getMessage() 
	{
		return "Course with courseCode: " + courseCode + " can't be deleted.";
	}
}
