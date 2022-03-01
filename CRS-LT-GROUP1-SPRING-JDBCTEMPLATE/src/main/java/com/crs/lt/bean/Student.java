package com.crs.lt.bean;

import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;

public class Student extends User {

	
	public String branchName;
	public String studentId; 
	int batch;
	boolean isApproved;
	boolean isRegisterd;
	public boolean isRegisterd() {
		return isRegisterd;
	}
	public void setRegisterd(boolean isRegisterd) {
		this.isRegisterd = isRegisterd;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public boolean isReportGenerated() {
		return isReportGenerated;
	}
	public void setReportGenerated(boolean isReportGenerated) {
		this.isReportGenerated = isReportGenerated;
	}
	boolean isPaid;
	boolean isReportGenerated;
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String userId, String name, RoleConstant role, String password, GenderConstant gender,
			String address) {
		super(userId, name, role, password, gender, address);
		// TODO Auto-generated constructor stub
	}
	public Student(String userId, String name, RoleConstant role, String password, GenderConstant gender,
			String address, String branchName, String studentId, int batch, boolean isApproved, boolean isRegisterd,
			boolean isPaid, boolean isReportGenerated) {
		super(userId, name, role, password, gender, address);
		this.branchName = branchName;
		this.studentId = studentId;
		this.batch = batch;
		this.isApproved = isApproved;
		this.isRegisterd = isRegisterd;
		this.isPaid = isPaid;
		this.isReportGenerated = isReportGenerated;
	}
	
	
	
	
}
