/**
 * 
 */
package com.crs.lt.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.crs.lt.bean.Student;
import com.crs.lt.exceptions.StudentNotRegisteredException;

/**
 * @author user203
 *
 */
//@Repository
public interface StudentDAOInterface {

	/**
	 * Method to add student to database
	 * @param student: student object containing all the fields
	 * @return true if student is added, else false
	 * @throws StudentNotRegisteredException
	 * @throws SQLException 
	 */
	public String addStudent(Student student) throws StudentNotRegisteredException, SQLException;
	
	

	/**
	 * Method to retrieve Student Id from User Id
	 * @param userId
	 * @return Student Id
	 * @throws SQLException 
	 */
	public String getStudentId(String userId) throws SQLException;
	
	/**
	 * Method to check if Student is approved
	 * @param studentId
	 * @return boolean indicating if student is approved
	 * @throws SQLException 
	 */
	public boolean isApproved(String studentId) throws SQLException;
}
