package com.crs.lt.bean;
import java.io.Serializable;

import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;

public class User {

	private String userId;
	private String name;
	private RoleConstant role;
	private String password;
	private GenderConstant gender;
	private String address;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoleConstant getRole() {
		return role;
	}
	public void setRole(RoleConstant roleConstant) {
		this.role = roleConstant;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public GenderConstant getGender() {
		return gender;
	}
	public void setGender(GenderConstant genderConstant) {
		this.gender = genderConstant;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(String userId, String name, RoleConstant role, String password, GenderConstant gender, String address) {
		this.userId = userId;
		this.name = name;
		this.role = role;
		this.password = password;
		this.gender = gender;
		this.address = address;
	}
	public User() {
		
		// TODO Auto-generated constructor stub
	}
	


	
}
