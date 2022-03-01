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

import com.crs.lt.bean.Course;
import com.crs.lt.bean.EnrolledStudent;
import com.crs.lt.bean.Professor;
import com.crs.lt.configuration.ConfigurationJDBC;
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.GradeNotAddedException;


/**
 * @author user203
 *
 */
@Repository
public class ProfessorDaoOperation implements ProfessorDOAInterface {
	private static Logger logger=Logger.getLogger(ProfessorDaoOperation.class);

	@Autowired
	private ConfigurationJDBC configurationJDBC;
	@Override
	public List<Course> getCoursesByProfessor(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnrolledStudent> getEnrolledStudents(String CourseId) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = configurationJDBC.dataSource().getConnection();
		List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueryConstant.GET_ENROLLED_STUDENT);
			statement.setString(1, CourseId);
			
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				//public EnrolledStudent(String courseCode, String courseName, int studentId) 
				enrolledStudents.add(new EnrolledStudent(results.getString("courseCode"),results.getString("courseName"),results.getString("studentId")));
			}
		}
		catch(SQLException e)
		{
			logger.error("Exception in DAO" + e.getMessage());
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
		return enrolledStudents;

	}

	@Override
	public Boolean addGrade(String studentId, String courseCode, String grade) throws GradeNotAddedException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueryConstant.ADD_GRADE);
			
			statement.setString(1, grade);
			statement.setString(2, courseCode);
			statement.setString(3, studentId);
			
			int row = statement.executeUpdate();
			
			if(row==1)
				return true;
			else
				return false;
		}
		catch(SQLException e)
		{
			logger.error("Exception in DAO" + e.getMessage());
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
	public String getProfessorById(String profId) throws SQLException {
		// TODO Auto-generated method stub
		{
			String prof_Name = null;
			Connection connection = configurationJDBC.dataSource().getConnection();
			try 
			{
				PreparedStatement statement = connection.prepareStatement(SQLQueryConstant.GET_PROF_NAME);
				
				statement.setString(1, profId);
				ResultSet rs = statement.executeQuery();
				rs.next();
				
				prof_Name = rs.getString(1);
				
			}
			catch(SQLException e)
			{
				logger.error(e.getMessage());
			}
			finally
			{
				try 
				{
					connection.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
			return prof_Name;
		}
	}

	@Override
	public Professor getProfessorByUserId(String userId) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SQLQueryConstant.GET_PROFESSOR_BY_USER_ID);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Professor professor = new Professor();
                professor.setProfessorID(userId);
                professor.setDesignation(rs.getString("designation"));
                professor.setDepartment(rs.getString("department"));
                professor.setName("name");
              professor.setAddress("address");
              professor.setUserId(userId);
              professor.setRole(RoleConstant.PROFESSOR);
                return professor;
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());

        }
        return null;
	}

}
