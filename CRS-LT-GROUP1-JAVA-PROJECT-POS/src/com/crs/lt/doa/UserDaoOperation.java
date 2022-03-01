package com.crs.lt.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.crs.lt.bean.User;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.UserNotFoundException;
import com.crs.lt.utils.DBUtils;
import com.crs.lt.utils.DatabaseJDBC;

public class UserDaoOperation implements UserDAOInterface{
	private static Logger logger=Logger.getLogger(UserDaoOperation.class);

	 //List users;
	
	@Override
	public boolean verifyCredentials(String userId, String password)throws UserNotFoundException {
		// TODO Auto-generated method stub
//		boolean verified = false;
//		
//		for (User u : users) {
//			if(u.getPassword().equals(password)){
//				verified = true;
//			}
//		}
//		return verified;
		
		Connection connection = DBUtils.getConnection();
		try
		{
			//open db connection
			//String sql="select password from user where userId = ?";
			
			PreparedStatement stmt = null;
		      stmt = connection.prepareStatement(SQLQueryConstant.CHECK_CREDENTIALS_USER_DETAILS);
		      stmt.setString(1, userId); 
		      ResultSet resultSet = stmt.executeQuery();
			if(!resultSet.next())
				throw new UserNotFoundException(userId);

			else if(password.equals(resultSet.getString("password")))
			{
				
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(SQLException ex)
		{
			logger.error("Not found", ex);
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
		}
		return false;
	}
	@Override
	public String getRole(String userId) {
		// TODO Auto-generated method stub
		Connection connection=DatabaseJDBC.getConnection();
		try {
			connection=DatabaseJDBC.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLQueryConstant.GET_ROLE_TYPE);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();

			if(rs.next())
			{
				return rs.getString("role");
			}
				
		}
		catch(Exception e)
		{
			logger.error("Exception in DAO" + e.getMessage());
			
		}
		
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//logger.error("Exception in DAO" + e.getMessage());
			}
		}
		return null;
	}
	@Override
	public boolean updatePassword(String userID, String newPassword) {
		// TODO Auto-generated method stub
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueryConstant.UPDATE_PASSWORD);
			
			statement.setString(1, newPassword);
			statement.setString(2, userID);
			
			int row = statement.executeUpdate();
			
			if(row==1)
				return true;
			else
				return false;
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
		}
		return false;
	}
	@Override
	public String getName(String userId) {
		// TODO Auto-generated method stub
		Connection connection=DatabaseJDBC.getConnection();
		try {
			connection=DatabaseJDBC.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLQueryConstant.GET_USER_NAME);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();

			if(rs.next())
			{
				return rs.getString("name");
			}
				
		}
		catch(Exception e)
		{
			logger.error("Exception in DAO" + e.getMessage());
			
		}
		
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//logger.error("Exception in DAO" + e.getMessage());
			}
		}
		return null;
	}
}
