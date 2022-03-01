package com.crs.lt.business;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.Grade;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.SeatNotAvailableException;

/**
 * @author user203
 *
 */
public interface RegistrationInterface {

	/**
	 * Method to add courses 
	 * @param courseCode
	 * @paraam studentId
	 * @param courses list
	 * @throws CourseNotFoundException
	 * @throws SQLException
	 * @throws SeatNotAvailableException
	 */
	public boolean addCourse(String courseCode, String studentId, List<Course> availableCourseList) throws CourseNotFoundException, SQLException, SeatNotAvailableException;
	
	/**
	 * Method to set registration status 
	 
	 * @param studentId
	 
	 * @throws SQLException
	 */
	void setRegistrationStatus(String studentId) throws SQLException;
	
	/**
	 * Method to get registration status
	 * @param studentId
	 * @throws SQLException
	 */
	boolean getRegistrationStatus(String studentId)throws SQLException;
	
	/**
	 * Method to get payment status
	 * @paraam studentId
	 * @throws SQLException	
	 */
	boolean getPaymentStatus(String studentId)throws SQLException;
	/**
	 * Method to view registered courses 
	 * @paraam studentId
	 * @throws SQLException
	 */
	List<Course> viewRegisteredCourses(String studentId)throws SQLException;
	
	/**
	 * Method to view courses 
	 * @paraam studentId
	 * @throws SQLException
	 */
	List<Course> viewCourses(String studentId)throws SQLException;
	
	/**
	 * Method to view grades
	 * @paraam studentId
	 * @throws SQLException
	 */
	List<Grade> viewGradeCard(String studentId)throws SQLException;
	
	/**
	 * Method to calculate fees for courses 
	 * @paraam studentId
	 * @throws SQLException
	 */
	double calculateFee(String studentId)throws SQLException;
	
	/**
	 * Method to drop courses 
	 * @param courseCode
	 * @param studentId
	 * @param registeredCourseList
	 * @throws CourseNotFoundException
	 * @throws SQLException
	 */
	boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList) throws CourseNotFoundException, SQLException;
	

	/**
	 * Method to get report status 
	 * @param studentId
	 * @throws SQLException
	 */
	public boolean isReportGenerated(String studentId) throws SQLException;
	
	/**
	 * Method to payment status
	 * @param studentId
	 * @throws SQLException
	 */
	public void setPaymentStatus(String studentId)throws SQLException;
	
}
