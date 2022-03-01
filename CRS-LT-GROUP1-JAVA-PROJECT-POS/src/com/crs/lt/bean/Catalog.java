package com.crs.lt.bean;

import java.util.Date;
import java.util.List;

public class Catalog {

	private String courseName;
	private String courseCode;
	private String description;
	public Catalog(){
		
	}
	public Catalog(String courseName, String courseCode, String description
			) {
		super();
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.description = description;
		
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
