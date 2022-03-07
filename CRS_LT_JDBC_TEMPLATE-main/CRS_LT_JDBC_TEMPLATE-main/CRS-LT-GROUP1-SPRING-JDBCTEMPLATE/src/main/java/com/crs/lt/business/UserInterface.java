package com.crs.lt.business;

import java.sql.SQLException;

import com.crs.lt.exceptions.UserNotFoundException;

/**
 * @author user203
 *
 */
public interface UserInterface {

	/**
	 * Method to get role
	 * @param userID
	 * @throws SQLException 
	 */
	String getRole(String userId) throws SQLException;

	/**
	 * Method to verify User credentials
	 * @param userID
	 * @param password
	 * @return boolean indicating if user exists in the database
	 * @throws SQLException 
	 */
	boolean verifyCredentials(String userId, String password) throws UserNotFoundException, SQLException;
	

	/**
	 * Method to update password
	 * @param userID
	 * @param new password
	 * @throws SQLException 
	 */
	boolean updatePassword(String userId, String newPassword) throws SQLException;
	
	/* Method to get name of the user
	 * @param userId
	 */
	String getName(String userId) throws SQLException;
}
