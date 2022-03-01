package com.crs.lt.business;

import org.apache.log4j.Logger;

import com.crs.lt.bean.User;
import com.crs.lt.doa.UserDaoOperation;
import com.crs.lt.doa.UserDAOInterface;
import com.crs.lt.exceptions.UserNotFoundException;

public class UserService implements UserInterface {
	private static Logger logger = Logger.getLogger(UserService.class);

	UserDAOInterface userDAOInterface = new UserDaoOperation();
	public boolean verifyCredentials(String userId, String password)throws UserNotFoundException {
		try {
			return userDAOInterface.verifyCredentials(userId, password);
		}
		finally {
			
		}
	}
	@Override
	public String getRole(String userId) {
		// TODO Auto-generated method stub
		return userDAOInterface.getRole(userId);
	}
	@Override
	public boolean updatePassword(String userID, String newPassword) {
		// TODO Auto-generated method stub
		return userDAOInterface.updatePassword(userID, newPassword);
	}
	@Override
	public String getName(String userId) {
		// TODO Auto-generated method stub
		return userDAOInterface.getName(userId);
	}
}
