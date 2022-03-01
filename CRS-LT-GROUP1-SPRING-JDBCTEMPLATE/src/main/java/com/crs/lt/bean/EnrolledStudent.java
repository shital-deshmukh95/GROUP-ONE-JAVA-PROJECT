package com.crs.lt.bean;

public class EnrolledStudent {

	private String courseCode;
	private String courseName;
	private String studentId;
	
	
	public EnrolledStudent(String courseCode, String courseName,
			String studentId) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.studentId = studentId;
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
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public EnrolledStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
