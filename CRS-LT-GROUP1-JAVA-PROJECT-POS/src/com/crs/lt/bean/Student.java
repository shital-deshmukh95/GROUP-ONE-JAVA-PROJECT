package com.crs.lt.bean;

public class Student extends User {

	
	public String branchName;
	public String studentId; 
	int batch;
	boolean isApproved;
	public Student(String userId, String name, String role, String password, String gender, String address,
			String branchName, String studentId, int batch, boolean isApproved) {
		super(userId, name, role, password, gender, address);
		this.branchName = branchName;
		this.studentId = studentId;
		this.batch = batch;
		this.isApproved = isApproved;
	}
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
	
	
}
