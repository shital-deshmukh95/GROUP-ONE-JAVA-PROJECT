package com.lt.crs.model;

/**
 * 
 * @author user219
 *  Model class for EnrolledStudent
 */
public class EnrolledStudent {
	
	private String courseCode;
	
	private String courseName;
	
	private String studentId;
	
	public EnrolledStudent() {
		super();
	}
	
	
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


	@Override
	public String toString() {
		return "EnrolledStudent [courseCode=" + courseCode + ", courseName=" + courseName + ", studentId=" + studentId
				+ "]";
	}
	
	
	
}
