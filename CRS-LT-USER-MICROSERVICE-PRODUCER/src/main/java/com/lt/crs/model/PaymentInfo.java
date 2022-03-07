package com.lt.crs.model;

public class PaymentInfo {
private String studentId ;
private double totalfee ;

public PaymentInfo() {
	super();
	// TODO Auto-generated constructor stub
}
public PaymentInfo(double totalfee) {
	super();
	this.totalfee = totalfee;
}
public String getStudentId() {
	return studentId;
}
public void setStudentId(String studentId) {
	this.studentId = studentId;
}
public double getTotalfee() {
	return totalfee;
}
public void setTotalfee(double totalfee) {
	this.totalfee = totalfee;
}
@Override
public String toString() {
	return "PaymentInfo [studentId=" + studentId + ", totalfee=" + totalfee + "]";
}


	
	
}
