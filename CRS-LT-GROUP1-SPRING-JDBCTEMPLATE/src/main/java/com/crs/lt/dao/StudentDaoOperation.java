package com.crs.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crs.lt.bean.Student;
import com.crs.lt.business.StudentService;
import com.crs.lt.configuration.ConfigurationJDBC;
import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.StudentNotRegisteredException;
import com.crs.lt.mapper.StudentMapper;


@Repository
public class StudentDaoOperation implements StudentDAOInterface{

	private static Logger logger = Logger.getLogger(StudentDaoOperation.class);
	
	@Autowired
	private ConfigurationJDBC configurationJDBC;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public String addStudent(Student student)  {
		// TODO Auto-generated method stub
		String studentId = null;
		String userId = student.getUserId();
		
		String name = student.getName();
		String password = student.getPassword();
		String role = student.getRole().toString();
		String gender = student.getGender().toString();
		String address = student.getAddress();
		
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0");
		int rowsAffected =  jdbcTemplate.update(SQLQueryConstant.ADD_USER_QUERY,userId, name,password, role, gender, address);
		
			
			
			if(rowsAffected==1)
			{
				
				studentId = student.getUserId();
				System.out.println(studentId);
				//add the student record
				String branch = student.getBranchName();
				Integer batch = student.getBatch();
				jdbcTemplate.update(SQLQueryConstant.ADD_STUDENT,studentId, branch,batch);
				jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1");
				return studentId;
			}
			jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1");
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
		Boolean isApproved = jdbcTemplate.queryForObject(SQLQueryConstant.IS_APPROVED, new Object[] {studentId}, Boolean.class);
		System.out.println("getRegistrationStatus: " +studentId);
		return isApproved;
	}

}
