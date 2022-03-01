package com.crs.lt.business;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.lt.bean.User;
import com.crs.lt.dao.UserDAOInterface;
import com.crs.lt.dao.UserDaoOperation;
import com.crs.lt.exceptions.UserNotFoundException;

@Service
public class UserService implements UserInterface {
	private static Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserDaoOperation userDAOInterface;

	public static User user = null;

//UserDAOInterface userDAOInterface = new UserDaoOperation();
	public boolean verifyCredentials(String userId, String password) throws UserNotFoundException, SQLException {
		return userDAOInterface.verifyCredentials(userId, password);
	}

	@Override
	public String getRole(String userId) throws SQLException {
// TODO Auto-generated method stub
		return userDAOInterface.getRole(userId);
	}

	@Override
	public boolean updatePassword(String userID, String newPassword) throws SQLException {
		return userDAOInterface.updatePassword(userID, newPassword);
	}

	@Override
	public String getName(String userId) throws SQLException {
// TODO Auto-generated method stub
		return userDAOInterface.getName(userId);
	}
}