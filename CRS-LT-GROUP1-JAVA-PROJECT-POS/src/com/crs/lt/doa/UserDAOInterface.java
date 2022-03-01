package com.crs.lt.doa;

import com.crs.lt.exceptions.UserNotFoundException;

public interface UserDAOInterface {

	public boolean verifyCredentials(String userId,String password) throws UserNotFoundException;
	public String getRole(String userId);
	public boolean updatePassword(String userID, String newPassword);
	public String getName(String userId);
}
