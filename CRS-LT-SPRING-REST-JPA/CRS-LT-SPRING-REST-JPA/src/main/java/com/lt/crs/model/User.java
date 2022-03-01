package com.lt.crs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;


/**
 * 
 * @author user215
 *  Model class for User
 */
@Entity
@Table(name = "user")
public class User {
	
	@Id
	public String userId;
	
	@Column(name = "password")
	public String password;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "gender")
	public String gender;
	
	@Column(name = "address")
	public String address;
	
	@Column(name = "role")
	public String role;

	public User() {
		super();
	}

	public User(String userId, String password, String name, String gender, String address, String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.role = role;
	}

	public User(String userId2, String name2, RoleConstant role2, String password2, GenderConstant gender2,
			String address2) {
		this.userId = userId;
		this.name = name;
		this.role = role;
		this.password = password;
		this.gender = gender;
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ ", address=" + address + ", role=" + role + "]";
	}


}
