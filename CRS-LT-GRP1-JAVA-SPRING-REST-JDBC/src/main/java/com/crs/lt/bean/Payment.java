package com.crs.lt.bean;

public class Payment {

	Student stud;
	private String InvoiceID;
	private float fee;
	private boolean status;
	
	public Payment(Student stud, String invoiceID, float fee, boolean status) {
		super();
		this.stud = stud;
		InvoiceID = invoiceID;
		this.fee = fee;
		this.status = status;
	}
	public Student getStud() {
		return stud;
	}
	public void setStud(Student stud) {
		this.stud = stud;
	}
	public String getInvoiceID() {
		return InvoiceID;
	}
	public void setInvoiceID(String invoiceID) {
		InvoiceID = invoiceID;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
