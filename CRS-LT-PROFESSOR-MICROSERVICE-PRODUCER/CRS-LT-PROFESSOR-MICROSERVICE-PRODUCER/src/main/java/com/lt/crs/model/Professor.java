package com.lt.crs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;

/**
 * 
 * @author user219
 *  Model class for Professor
 */
@Entity
@Table(name = "professor")
public class Professor  {

	@Id
	@Column(name = "instructorId")
	private String professorID;

	@Column(name = "department")
	private String department;

	@Column(name = "designation")
	private String designation;
	
//	@Transient
//	public  String address;
//	
//	@Transient
//	public  String gender;
//	
//	@Transient
//	public  String name;
//	
//	@Transient
//	public  String password;
//	
//	@Transient
//	public  String role="PROFESSOR";

	public Professor() {
		super();
	}

	

	public Professor(String userId, String name, RoleConstant role, String password, GenderConstant gender,
			String address) {
		super();
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
