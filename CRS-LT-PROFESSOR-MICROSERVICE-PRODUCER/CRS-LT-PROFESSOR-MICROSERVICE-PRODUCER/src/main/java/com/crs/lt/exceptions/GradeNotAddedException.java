package com.crs.lt.exceptions;
/**
 * 
 * @author user215
 *
 */
public class GradeNotAddedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentId;

	 public GradeNotAddedException(String studentId2)
	 {
		 this.studentId=studentId2;
	 }
	 

	 public String getStudentId()
	 {
		 return studentId;
	 }
	
	 
	 public String getMessage() 
	 {
			return "Student with id: " + studentId + "hasn't been alloted a grade yet";
	 }
}
