package com.crs.lt.business;

import java.sql.SQLException;

import com.crs.lt.constant.GenderConstant;
import com.crs.lt.exceptions.StudentNotRegisteredException;

/**
 * @author user203
 *
 */
public interface StudentInterface {
	/**
	 * Method to register student
	 * @param studentId
	 * @param studentName
	 * @param password
	 * @param gender 
	 * @param batch
	 * @throws SQLException 
	 */
	public String register(String name,String userID,String password,GenderConstant gender, int batch,String branch,String address) throws StudentNotRegisteredException, SQLException; 
	
	/**
	 * Method to get Student ID from User ID
	 * @param userId
	 * @return Student ID
	 * @throws SQLException 
	 */
	public String getStudentId(String userId) throws SQLException;
	/**
	 * Method to get student approved status
	 * @param studentId
	 * @throws SQLException 
	 */
    public boolean isApproved(String studentId) throws SQLException;

}
