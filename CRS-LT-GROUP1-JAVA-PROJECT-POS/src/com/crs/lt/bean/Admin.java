package com.crs.lt.bean;

import java.util.Date;

public class Admin extends User{


	public Admin(String userId, String name, String role, String password, String gender, String address,
			String adminID) {
		super(userId, name, role, password, gender, address);
		this.adminID = adminID;
	}

	private String adminID;

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	

}
