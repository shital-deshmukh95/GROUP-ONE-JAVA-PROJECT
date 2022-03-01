package com.crs.lt.bean;

public class RegisteredCourse {

	Course course;
	private String studentId;
	private String grade = "A";

	public RegisteredCourse(Course course, String studentId, String grade) {
		super();
		this.course = course;
		this.studentId = studentId;
		this.grade = grade;
	}
	public RegisteredCourse() {
		// TODO Auto-generated constructor stub
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = new Course(course.getCourseCode(), course.getCourseName(), course.getInstructorId() ,course.getAvailable_seats());
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	
}
