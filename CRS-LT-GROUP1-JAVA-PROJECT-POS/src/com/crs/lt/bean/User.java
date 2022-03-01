package com.crs.lt.bean;

import java.io.Serializable;

public abstract class User implements Serializable {

	private String userId;
	private String name;
	private String role;
	private String password;
	private String gender;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(String userId, String name, String role, String password, String gender, String address) {
		super();
		this.userId = userId;
		this.name = name;
		this.role = role;
		this.password = password;
		this.gender = gender;
		this.address = address;
	}
	


	
}
