package com.crs.lt.exceptions;
/**
 * 
 * @author user215
 *
 */
public class StudentNotRegisteredException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentName;
	 
	 public StudentNotRegisteredException(String studentName)
	 {
		 this.studentName=studentName;
	 }
	 
	 /**
	  * getter function for studentName
	  * @return
	  */
	 public String getStudentName()
	 {
		 return studentName;
	 }
}
