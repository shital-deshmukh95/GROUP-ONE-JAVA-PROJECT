package com.crs.lt.bean;

import java.util.Date;

import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;

public class Professor extends User {

	
	private String professorID;
	private String department;
	private String designation;
	
	public String getProfessorID() {
		return professorID;
	}
	public void setProfessorID(String professorID) {
		this.professorID = professorID;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Professor(String userId, String name, RoleConstant role, String password, GenderConstant gender, String address,
			String professorID, String department, String designation) {
		super(userId, name, role, password, gender, address);
		this.professorID = professorID;
		this.department = department;
		this.designation = designation;
	}
	public Professor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Professor(String userId, String name, RoleConstant role, String password, GenderConstant gender,
			String address) {
		super(userId, name, role, password, gender, address);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
