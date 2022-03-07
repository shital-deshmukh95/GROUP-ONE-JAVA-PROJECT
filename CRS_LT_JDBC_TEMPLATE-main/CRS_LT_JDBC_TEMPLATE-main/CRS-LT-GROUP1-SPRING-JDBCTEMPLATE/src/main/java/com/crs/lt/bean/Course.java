package com.crs.lt.bean;

public class Course {
	private String courseCode;
	private String courseName;
	private String instructorId;
	private int available_seats = 10;
	
	
	public Course() {
		// TODO Auto-generated constructor stub
	}
	

	public Course(String courseCode, String courseName, String instructorId,
			int available_seats) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.instructorId = instructorId;
		this.available_seats = available_seats;
	}


	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}
	public int getAvailable_seats() {
		return available_seats;
	}
	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}
	
}
