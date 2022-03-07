package com.crs.lt.bean;

import java.util.Date;

import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;

public class Admin extends User{


	public Admin(String userId, String name, RoleConstant role, String password, GenderConstant gender, String address,
			String adminID) {
		super(userId, name, role, password, gender, address);
		this.adminID = adminID;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	private String adminID;

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	

}
