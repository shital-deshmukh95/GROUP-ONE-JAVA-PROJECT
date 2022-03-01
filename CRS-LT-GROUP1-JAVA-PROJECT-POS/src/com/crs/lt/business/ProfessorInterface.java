package com.crs.lt.business;

import java.sql.SQLException;
import java.util.List;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.EnrolledStudent;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.GradeNotAddedException;

/**
 * @author user203
 *
 */
public interface ProfessorInterface {

	/**
	 * Method to add grade for students
	 * @param courseCode
	 * @param studentId 
	 * @param garde
	 * @throws GradeNotAddedException  
	 */
	public boolean addGrade(String studentID, String courseID, String grade) throws GradeNotAddedException;
	
	/**
	 * Method to view courses
	 * @param profId 
	 */
	public List<Course> viewCourses(String profID);
	String getProfessorById(String profId);
	
	/**
	 * Method to view- enrolled students
	 * @param profId
	 * @throws SQLException  
	 */
	public List<EnrolledStudent> viewEnrolledStudents(String profId)throws SQLException;
}
