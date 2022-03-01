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

import com.crs.lt.bean.Course;
import com.crs.lt.bean.EnrolledStudent;
import com.crs.lt.bean.Grade;
import com.crs.lt.bean.Professor;
import com.crs.lt.configuration.ConfigurationJDBC;
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.GradeNotAddedException;
import com.crs.lt.mapper.CatalogMapper;
import com.crs.lt.mapper.EnrolledStudentMapper;
import com.crs.lt.mapper.GradeMapper;
import com.crs.lt.mapper.ProfessorMapper;
@Repository
public class ProfessorDaoOperation implements ProfessorDOAInterface
{
	private static Logger logger=Logger.getLogger(ProfessorDaoOperation.class);

	@Autowired
	private ConfigurationJDBC configurationJDBC;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<Course> getCoursesByProfessor(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnrolledStudent> getEnrolledStudents(String CourseId) throws SQLException {
		// TODO Auto-generated method stub

		List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
		
			enrolledStudents = jdbcTemplate.query(SQLQueryConstant.GET_ENROLLED_STUDENT, new Object[] {CourseId}, new EnrolledStudentMapper());
			
			return enrolledStudents;

	}

	@Override
	public Boolean addGrade(String studentId, String courseCode, String grade) throws GradeNotAddedException, SQLException {
	
		int gradeAdded = jdbcTemplate.update(SQLQueryConstant.ADD_GRADE, grade, courseCode, studentId);
		if (gradeAdded >0) {
			return true;
		}
		return false;
	}

	//Check and change 
	@Override
	public List<Professor> getProfessorById(String profId) throws SQLException {
		// TODO Auto-generated method stub
		{

			List<Professor> ProfessorById=new ArrayList<Professor>();
								
			ProfessorById	= jdbcTemplate.query(SQLQueryConstant.GET_PROF_NAME, new ProfessorMapper());
			
			return ProfessorById;
		}
	}

	
	//Check and change 
	@Override
	public Professor getProfessorByUserId(String userId) throws SQLException {
		// TODO Auto-generated method stub
	
		Professor ProfessorByUserId =new Professor();
       
		ProfessorByUserId	= (Professor) jdbcTemplate.query(SQLQueryConstant.GET_PROFESSOR_BY_USER_ID, new ProfessorMapper());
            
               
		return ProfessorByUserId;       
              
       
	}
}
