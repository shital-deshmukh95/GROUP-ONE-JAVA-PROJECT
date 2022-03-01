package com.crs.lt.exceptions;

public class GradeNotAddedException extends Exception {
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
