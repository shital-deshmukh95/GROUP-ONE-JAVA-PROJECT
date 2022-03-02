package com.lt.crs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;

/**
 * 
 * @author user219
 *  Model class for Professor
 */
@Entity
@Table(name = "professor")
public class Professor extends User {

	
	@Column(name = "instructorId")
	private String professorID;

	@Column(name = "department")
	private String department;

	@Column(name = "designation")
	private String designation;

	public Professor() {
		super();
	}

	public Professor(String userId, String name, RoleConstant role, String password, GenderConstant gender, String address,
			String professorID, String department, String designation) {
		super(userId, name, role, password, gender, address);
		this.professorID = professorID;
		this.department = department;
		this.designation = designation;
	}

	public Professor(String userId, String name, RoleConstant role, String password, GenderConstant gender,
			String address) {
		super(userId, name, role, password, gender, address);
	}


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

	@Override
	public String toString() {
		return "Professor [professorID=" + professorID + ", department=" + department + ", designation=" + designation
				+ "]";
	}


}
