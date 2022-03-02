package com.lt.crs.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author user213
 *
 */

@Entity
@Table(name="registeredcourse")
public class RegisteredCourse {
	
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//int registerCourseId;
	@Id
	@Column(name = "studentId")
	public String studentId;

	@Column(name = "courseCode")
	public String courseCode;

	@Column(name = "grade")
	public String grade;



	/**
	 * @param studentId
	 * @param courseCode
	 * @param grade
	 */
	public RegisteredCourse(String studentId, String courseCode, String grade) {
		super();
		this.studentId = studentId;
		this.courseCode = courseCode;
		this.grade = grade;
	}



	/**
	 *
	 */
	public RegisteredCourse() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}



	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}



	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}



	/**
	 * @param courseCode the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}



	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}



	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}



	@Override
	public String toString() {
		return "RegisteredCourse [studentId=" + studentId + ", courseCode=" + courseCode + ", grade=" + grade + "]";
	}

}
