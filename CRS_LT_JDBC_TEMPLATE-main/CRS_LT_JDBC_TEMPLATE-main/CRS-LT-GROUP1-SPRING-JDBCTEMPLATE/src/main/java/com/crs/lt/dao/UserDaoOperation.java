package com.crs.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.User;
import com.crs.lt.configuration.ConfigurationJDBC;
import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.UserNotFoundException;
import com.crs.lt.mapper.CatalogMapper;
import com.crs.lt.mapper.UserMapper;


@Repository
public class UserDaoOperation implements UserDAOInterface{
	private static Logger logger=Logger.getLogger(UserDaoOperation.class);
	@Autowired
	private ConfigurationJDBC configurationJDBC;
	
	@Autowired
	JdbcTemplate jdbcTemplate; 

	@SuppressWarnings("deprecation")
	@Override
	public boolean verifyCredentials(String userId, String password)throws UserNotFoundException, SQLException {
		boolean isVerify = false ;
		List<User> userList = new ArrayList<>();
		System.out.println("verifyCredentials");
		userList =  jdbcTemplate.query(SQLQueryConstant.GET_USER_DETAILS,new Object[] {userId} , new UserMapper());
		System.out.println("***************size"+userList.size());


		if(userList.size() == 0) {
			throw new UserNotFoundException(userId);
		}
		User user =	userList.get(0);
		 if(password !=null && user.getPassword().equals(password))
		{
			isVerify = true;
		}
		return isVerify;

	}

	@SuppressWarnings("deprecation")
	@Override
	public String getRole(String userId) throws SQLException {
		String role = null;
		try {
			List<User> userList = new ArrayList<>();
			userList =  jdbcTemplate.query(SQLQueryConstant.GET_USER_DETAILS, new Object[] {userId},new UserMapper());
			
			if(userList.size() == 0) {
				throw new Exception();
			}
			User user =	userList.get(0);
			if(user!=null)
			{
				role = user.getRole().toString(); 
			}

		}
		catch(Exception e)
		{
			System.out.println("Exception in DAO" + e.getMessage());

		}

		return role;
	}
	@Override
	@Transactional
	public boolean updatePassword(String userID, String newPassword) throws SQLException {
		int row =  jdbcTemplate.update(SQLQueryConstant.UPDATE_PASSWORD,newPassword,userID);
		System.out.println("Updated row :" + row);
		  return  row==1 ? true:false;
		  
	}
	@Override
	public String getName(String userId) throws SQLException {
		String name = null;
		try {
			List<User> userList = new ArrayList<>();
			userList =  jdbcTemplate.query(SQLQueryConstant.GET_USER_DETAILS, new Object[] {userId},new UserMapper());
			
			if(userList.size() == 0) {
				throw new Exception();
			}
			User user =	userList.get(0);
			if(user!=null)
			{
				name = user.getName(); 
			}

		}
		catch(Exception e)
		{
			System.out.println("Exception in DAO" + e.getMessage());

		}

		return name;
	}
}
