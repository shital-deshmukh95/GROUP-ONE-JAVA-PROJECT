package com.crs.lt.business;

import java.sql.SQLException;

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
	 */
	public String register(String name,String userID,String password,String gender, int batch,String branch,String address) throws StudentNotRegisteredException; 
	
	/**
	 * Method to get studentID
	 * @param userID
	 */
	public String getStudentId(String userId);
	
	/**
	 * Method to get student approved status
	 * @param studentId
	 */
    public boolean isApproved(String studentId);

}
