package com.crs.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crs.lt.bean.User;
import com.crs.lt.configuration.ConfigurationJDBC;
import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.UserNotFoundException;


@Repository
public class UserDaoOperation implements UserDAOInterface{
	private static Logger logger=Logger.getLogger(UserDaoOperation.class);

	 //List users;
	
	@Autowired
	private ConfigurationJDBC configurationJDBC;
	@Override
	public boolean verifyCredentials(String userId, String password)throws UserNotFoundException, SQLException {

		Connection connection = configurationJDBC.dataSource().getConnection();
		try
		{
			//open db connection
			//String sql="select password from user where userId = ?";
			
			PreparedStatement stmt = null;
		      stmt = connection.prepareStatement(SQLQueryConstant.CHECK_CREDENTIALS_USER_DETAILS);
		      stmt.setString(1, userId); 
		      
		      ResultSet resultSet = stmt.executeQuery();
		      System.out.println(resultSet);
			if(!resultSet.next())
				throw new UserNotFoundException(userId);

//			  if (resultSet.next()) {
//	                User user = new User(
//	                		resultSet.getString("userId"),
//	                		resultSet.getString("name"),
//	                		resultSet.getString("address"),
//	                		resultSet.getString("password"),
//	                		resultSet.getString(RoleConstant.stringToName("role")),
//	                		resultSet.getLong(GenderConstant.stringToGender("gender"))
//	                );
//	                user.setId(rs.getInt("id"));
//	                return user;
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
	public String getRole(String userId) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();
		try {
			//connection=DBUtils.getConnection();
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
	public boolean updatePassword(String userID, String newPassword) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();
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
	public String getName(String userId) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();
		try {
			
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
