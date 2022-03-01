package com.crs.lt.bean;

import java.util.List;

public class RegisterCourseModelClass {
	
	public List<String> courseList;

	public RegisterCourseModelClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterCourseModelClass(List<String> courseList) {
		super();
		this.courseList = courseList;
	}

	public List<String> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<String> courseList) {
		this.courseList = courseList;
	}

}
