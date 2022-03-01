package com.crs.lt.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.crs.lt.bean.User;
import com.crs.lt.exceptions.UserNotFoundException;

//@Repository
public interface UserDAOInterface {

	//public User verifyCredentials(String userId,String password) throws UserNotFoundException, SQLException;
	public boolean verifyCredentials(String userId,String password) throws UserNotFoundException, SQLException;
	public String getRole(String userId) throws SQLException;
	public boolean updatePassword(String userID, String newPassword) throws SQLException;
	public String getName(String userId)throws SQLException;
}
