package com.crs.lt.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SemRegistration {

	private int studentID;
	private int sem;
	private Date date;
	private List<Course> courseList = new ArrayList<Course>();
	public int getStudenID() {
		return studentID;
	}
	public void setStudentID(int studenID) {
		this.studentID = studenID;
	}
	public int getSem() {
		return sem;
	}
	public void setSem(int sem) {
		this.sem = sem;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
}
