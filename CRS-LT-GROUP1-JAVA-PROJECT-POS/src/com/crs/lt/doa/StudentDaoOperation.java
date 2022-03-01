package com.crs.lt.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.crs.lt.bean.Student;
import com.crs.lt.business.StudentService;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.StudentNotRegisteredException;
import com.crs.lt.utils.DBUtils;

public class StudentDaoOperation implements StudentDAOInterface{

	private static Logger logger = Logger.getLogger(StudentDaoOperation.class);
	@Override
	public String addStudent(Student student) throws StudentNotRegisteredException {
		// TODO Auto-generated method stub

Connection connection=DBUtils.getConnection();
		
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

				//add the student record
				//"insert into student (userId,branchName,batch,isApproved) values (?,?,?,?)";
				PreparedStatement preparedStatementStudent;
				preparedStatementStudent=connection.prepareStatement(SQLQueryConstant.ADD_STUDENT,Statement.RETURN_GENERATED_KEYS);
				preparedStatementStudent.setString(1,student.getUserId());
				preparedStatementStudent.setString(2, student.getBranchName());
				preparedStatementStudent.setInt(3, student.getBatch());
				//preparedStatementStudent.setBoolean(4, true);
				preparedStatementStudent.executeUpdate();
				ResultSet results=preparedStatementStudent.getGeneratedKeys();
				if(results.next())
					studentId=results.getString(1);
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
	
	

	@Override
	public String getStudentId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isApproved(String studentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
