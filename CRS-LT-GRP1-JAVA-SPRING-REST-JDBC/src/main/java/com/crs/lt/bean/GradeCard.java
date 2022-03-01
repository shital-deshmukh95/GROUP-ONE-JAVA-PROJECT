package com.crs.lt.bean;

import java.util.List;

public class GradeCard {

	Student stud;
	private int sem;
	private float scores;
	List<RegisteredCourse> regList ;
	public Student getStud() {
		return stud;
	}
	public void setStud(Student stud) {
		this.stud = stud;
	}
	public int getSem() {
		return sem;
	}
	public void setSem(int sem) {
		this.sem = sem;
	}
	public float getScores() {
		return scores;
	}
	public void setScores(float scores) {
		this.scores = scores;
	}
	public List<RegisteredCourse> getRegList() {
		return regList;
	}
	public void setRegList(List<RegisteredCourse> regList) {
		this.regList = regList;
	}
	
}
