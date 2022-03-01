package com.crs.lt.business;

import com.crs.lt.exceptions.UserNotFoundException;

/**
 * @author user203
 *
 */
public interface UserInterface {

	/**
	 * Method to get role
	 * @param userID
	 */
	String getRole(String userId);

	/**
	 * Method to verify User credentials
	 * @param userID
	 * @param password
	 * @return boolean indicating if user exists in the database
	 */
	boolean verifyCredentials(String userId, String password) throws UserNotFoundException;
	

	/**
	 * Method to update password
	 * @param userID
	 * @param new password
	 */
	boolean updatePassword(String userId, String newPassword);
	
	/* Method to get name of the user
	 * @param userId
	 */
	String getName(String userId);
}
