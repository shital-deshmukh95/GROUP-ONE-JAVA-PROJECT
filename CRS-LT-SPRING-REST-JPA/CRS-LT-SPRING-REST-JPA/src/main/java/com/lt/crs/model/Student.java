package com.lt.crs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author user215
 *  Model class for User
 */
@Entity
@Table(name = "student")
public class Student {

	@Id
	public String  studentId;
	
	@Column(name = "branch")
	public  String branch ;
	
	@Column(name = "batch")
	public String  batch;
	

	@Column(name = "isRegistered")
	public String  isRegistered ;
	

	@Column(name = "isPaid")
	public String  isPaid; 
	

	@Column(name = "isApproved")
	public String isApproved;
	

	@Column(name = "isReportGenerated")
	public  String isReportGenerated;

	

	public Student() {
		super();
	}

	public Student(String studentId, String branch, String batch, String isRegistered, String isPaid, String isApproved,
			String isReportGenerated) {
		super();
		this.studentId = studentId;
		this.branch = branch;
		this.batch = batch;
		this.isRegistered = isRegistered;
		this.isPaid = isPaid;
		this.isApproved = isApproved;
		this.isReportGenerated = isReportGenerated;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getIsRegistered() {
		return isRegistered;
	}

	public void setIsRegistered(String isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public String getIsReportGenerated() {
		return isReportGenerated;
	}

	public void setIsReportGenerated(String isReportGenerated) {
		this.isReportGenerated = isReportGenerated;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", branch=" + branch + ", batch=" + batch + ", isRegistered="
				+ isRegistered + ", isPaid=" + isPaid + ", isApproved=" + isApproved + ", isReportGenerated="
				+ isReportGenerated + "]";
	}

	
	
	
	
}
