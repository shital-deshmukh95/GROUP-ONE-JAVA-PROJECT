/**
 * 
 */
package com.lt.crs.model;

/**
 * @author user215
 *
 */
public class EnrolledStudentInfo {

	private String courseName;
	private String studentId;
	private String courseCode;
	/**
	 * @param instructorId
	 * @param studentId
	 * @param courseCode
	 */
	public EnrolledStudentInfo(String courseName, String studentId, String courseCode) {
		super();
		this.courseName = courseName;
		this.studentId = studentId;
		this.courseCode = courseCode;
	}
	/**
	 * 
	 */
	public EnrolledStudentInfo() {
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
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "EnrolledStudentInfo [courseName=" + courseName + ", studentId=" + studentId + ", courseCode="
				+ courseCode + "]";
	}
	
	
	
}
