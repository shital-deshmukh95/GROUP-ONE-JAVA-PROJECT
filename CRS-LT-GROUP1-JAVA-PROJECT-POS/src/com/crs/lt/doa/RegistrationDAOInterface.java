/**
 * 
 */
package com.crs.lt.doa;

import java.sql.SQLException;
import java.util.List;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.Grade;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.SeatNotAvailableException;

/**
 * @author user203
 *
 */
public interface RegistrationDAOInterface {

	
	/**
	 * Method to add courses 
	 * @param courseCode
	 * @param studentId
	 * @param courses list
	 * @throws CourseNotFoundException
	 * @throws SQLException
	 * @throws SeatNotAvailableException
	 */
	public boolean addCourse(String courseCode, String studentId) throws CourseNotFoundException, SQLException, SeatNotAvailableException;
	
	/**
	 * Method to set registration status 
	 
	 * @param studentId
	 
	 * @throws SQLException
	 */
	public void setRegistrationStatus(String studentId) throws SQLException;
	
	/**
	 * Method to get registration status
	 * @param studentId
	 * @throws SQLException
	 */
	public boolean getRegistrationStatus(String studentId) throws SQLException;
	
	/**
	 * Method to get payment status
	 * @param studentId
	 * @throws SQLException	
	 */
	public boolean getPaymentStatus(String studentId)throws SQLException;
	
	/**
	 * Method to view registered courses 
	 * @param studentId
	 * @throws SQLException
	 */
	public List<Course> viewRegisteredCourses(String studentId)throws SQLException;
	

	/**
	 * Method to view courses 
	 * @param studentId
	 * @throws SQLException
	 */
	public List<Course> viewCourses(String studentId)throws SQLException;
	
	/**
	 * Method to view grades
	 * @param studentId
	 * @throws SQLException
	 */
	public List<Grade> viewGradeCard(String studentId)throws SQLException;
	

	/**
	 * Method to calculate fees for courses 
	 * @paraam studentId
	 * @throws SQLException
	 */
	public double calculateFee(String studentId)throws SQLException;
	
	/**
	 * Method to drop courses 
	 * @param courseCode
	 * @param studentId
	 * @param registeredCourseList
	 * @throws CourseNotFoundException
	 * @throws SQLException
	 */
	public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList)throws CourseNotFoundException, SQLException;
	
	/**
	 * Method to get report status 
	 * @param studentId
	 * @throws SQLException
	 */
	public boolean isReportGenerated(String studentId)throws SQLException ;
	public void setPaymentStatus(String studentId)throws SQLException;
	public boolean seatAvailable(String courseCode) throws SeatNotAvailableException, SQLException;
}
