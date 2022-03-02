package com.lt.crs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;

@Entity
@Table(name = "admin")
public class Admin extends User {


	//@Id
	public String adminId;

	public Admin() {
		super();
	}

	public Admin(String userId2, String name2, RoleConstant role2, String password2, GenderConstant gender2,
			String address2) {
		super(userId2, name2, role2, password2, gender2, address2);
		// TODO Auto-generated constructor stub
	}

	public Admin(String userId, String password, String name, String gender, String address, String role) {
		super(userId, password, name, gender, address, role);
		// TODO Auto-generated constructor stub
	}

	public Admin(String userId, String name, String role, String password, String gender, String address,
			String adminID) {
		super(userId, name, role, password, gender, address);
		this.adminId = adminID;
	}
	
	public String getAdminID() {
		return adminId;
	}

	public void setAdminID(String adminID) {
		this.adminId = adminID;
	}

	@Override
	public String toString() {
		return "Admin [adminID=" + adminId + "]";
	}
	

}