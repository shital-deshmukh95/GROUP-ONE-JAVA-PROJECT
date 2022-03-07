package com.crs.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crs.lt.bean.Student;
import com.crs.lt.business.StudentService;
import com.crs.lt.configuration.ConfigurationJDBC;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.StudentNotRegisteredException;


@Repository
public class StudentDaoOperation implements StudentDAOInterface{

	private static Logger logger = Logger.getLogger(StudentDaoOperation.class);
	
	@Autowired
	private ConfigurationJDBC configurationJDBC;
	@Override
	public String addStudent(Student student) throws StudentNotRegisteredException, SQLException {
		// TODO Auto-generated method stub

Connection connection = configurationJDBC.dataSource().getConnection();
		
		String studentId=null;
		try
		{
			//open db connection
			PreparedStatement preparedStatement=connection.prepareStatement(SQLQueryConstant.ADD_USER_QUERY);
			preparedStatement.setString(1, student.getUserId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getPassword());
			preparedStatement.setString(4, student.getRole().toString());
			preparedStatement.setString(5, student.getGender().toString());
			preparedStatement.setString(6, student.getAddress());
			
			int rowsAffected=preparedStatement.executeUpdate();
			
			if(rowsAffected==1)
			{
				
				studentId = student.getUserId();
				System.out.println(studentId);
				//add the student record
				//"insert into student (userId,branchName,batch,isApproved) values (?,?,?,?)";
				PreparedStatement preparedStatementStudent;
				preparedStatementStudent=connection.prepareStatement(SQLQueryConstant.ADD_STUDENT,Statement.RETURN_GENERATED_KEYS);
				preparedStatementStudent.setString(1,student.getUserId());
				preparedStatementStudent.setString(2, student.getBranchName());
				preparedStatementStudent.setInt(3, student.getBatch());
				//preparedStatementStudent.setBoolean(4, true);
				
				preparedStatementStudent.executeUpdate();
				//ResultSet results=preparedStatementStudent.getGeneratedKeys();
				
			}
			
			
		}
		catch(Exception ex)
		{
			throw new StudentNotRegisteredException(ex.getMessage());

	}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				logger.info(e.getMessage()+"SQL error");
				//e.printStackTrace();
			}
		}
		return studentId;
	}
	
	



	/**
	 * Method to retrieve Student Id from User Id
	 * @param userId
	 * @return Student Id
	 * @throws SQLException 
	 */
	@Override
	public String getStudentId(String userId) throws SQLException {
		Connection connection = configurationJDBC.dataSource().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueryConstant.GET_STUDENT_ID);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				return rs.getString("studentId");
			}
				
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Method to check if Student is approved
	 * @param studentId
	 * @return boolean indicating if student is approved
	 * @throws SQLException 
	 */
	@Override
	public boolean isApproved(String studentId) throws SQLException {
		Connection connection = configurationJDBC.dataSource().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueryConstant.IS_APPROVED);
			statement.setString(1, studentId);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				return rs.getBoolean("isApproved");
			}
				
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		
		return false;
	}

}
