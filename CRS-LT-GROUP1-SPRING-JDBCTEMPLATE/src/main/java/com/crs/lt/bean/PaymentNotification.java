package com.crs.lt.bean;

public class PaymentNotification {

	
	private int stdID;
	private String referID;
	private String notiMessage;
	private int notiID;
	public int getStdID() {
		return stdID;
	}
	public void setStdID(int stdID) {
		this.stdID = stdID;
	}
	public String getReferID() {
		return referID;
	}
	public void setReferID(String referID) {
		this.referID = referID;
	}
	public String getNotiMessage() {
		return notiMessage;
	}
	public void setNotiMessage(String notiMessage) {
		this.notiMessage = notiMessage;
	}
	public int getNotiID() {
		return notiID;
	}
	public void setNotiID(int notiID) {
		this.notiID = notiID;
	}
	
}
