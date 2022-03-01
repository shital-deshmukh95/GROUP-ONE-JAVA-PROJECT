package com.crs.lt.doa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.Course;
import com.crs.lt.bean.Professor;
import com.crs.lt.bean.RegisteredCourse;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.utils.DBUtils;

public class AdminDoaOperation implements AdminDOAInterface {
	private static Logger logger=Logger.getLogger(AdminDoaOperation.class);

	private PreparedStatement statement = null;
	Connection connection = DBUtils.getConnection();
	@Override
	public List<Catalog> viewCourses() {
		// TODO Auto-generated method stub

		statement = null;
		List<Catalog> courseList = new ArrayList<>();
		try {
			
			
			statement = connection.prepareStatement(SQLQueryConstant.VIEW_COURSE_CATALOG);
			//statement.setInt(1, catalogId);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Catalog catalog = new Catalog();
				catalog.setCourseCode(resultSet.getString(1));
				catalog.setCourseName(resultSet.getString(2));
				catalog.setDescription(resultSet.getString(3));
				courseList.add(catalog);
				
			}
			
			logger.info("Number of courses in the Catalog are : " + courseList.size());
			
		}catch(SQLException e) {
			
			logger.error("Exception in DAO" + e.getMessage());
			
		}
		
		return courseList; 
	}

	@Override
	public void addProfessor(Professor professor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCourse(String coursecode)  throws CourseNotDeletedException, CourseNotFoundException{
		statement = null;
		try {
			//String sql = "delete from Course where courseCode = ?";
			statement = connection.prepareStatement(SQLQueryConstant.DELETE_COURSE_FROM_CATALOG);
			
			statement.setString(1,coursecode);
			int row = statement.executeUpdate();
			
			logger.info(row + " entries deleted.");
			if(row == 0) {
				logger.info(coursecode + " not in catalog!");
				
			}

			logger.info("Course with courseCode: " + coursecode + " deleted.");
			
		}catch(SQLException e) {
			
			logger.error("Exception in DAO" + e.getMessage());
		}
		
	}
		
	

	@Override
	public void addCourse(Catalog course) throws CourseExistsAlreadyException{
		// TODO Auto-generated method stub
		statement = null;
		try {
			
			//String sql = "insert into Course(courseCode, courseName, availableSeats, instructorId) values (?, ?, ?, ?)";
			statement = connection.prepareStatement(SQLQueryConstant.ADD_COURSE_CATALOG);
			
			statement.setString(1, course.getCourseCode());
			statement.setString(2, course.getCourseName());
			statement.setString(3, course.getDescription());

			int row = statement.executeUpdate();
			
			logger.info(row + " course added");
			if(row == 0) {
				logger.info("Course with courseCode: " + course.getCourseCode() + "not added ");
				
			}
			
			logger.info("Course with courseCode: " + course.getCourseCode() + " is added "); 
			
		}catch(SQLException e) {
			
			logger.error("Exception in DAO" + e.getMessage());
			
		}
	}

	@Override
	public void setGeneratedReportCardTrue(String studentid) {
		// TODO Auto-generated method stub
		String sql = SQLQueryConstant.SET_GENERATED_REPORT_CARD_TRUE;
		try {
		statement = connection.prepareStatement(sql);
		statement.setString(1, studentid);
		int row = statement.executeUpdate();
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		
	}

	@Override
	public List<RegisteredCourse> generateGradeCard(String studentid) {
		// TODO Auto-generated method stub
List<RegisteredCourse> CoursesOfStudent = new ArrayList<RegisteredCourse>();
		
		try {
					String sql = SQLQueryConstant.VIEW_REGISTRATION_COURSE;
					statement = connection.prepareStatement(sql);
					statement.setString(1, studentid);
					ResultSet resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						
						Course course = new Course();
						RegisteredCourse temp = new RegisteredCourse() ;
						course.setCourseCode(resultSet.getString(1));
						course.setCourseName(resultSet.getString(2));
						course.setInstructorId(resultSet.getString(3));
						course.setAvailable_seats(resultSet.getInt(4));
						
						
						temp.setCourse(course);
						System.out.println("course object generated");
						temp.setStudentId(studentid);
						
						
						temp.setGrade(resultSet.getString(8));
						
						System.out.println("graded");
						CoursesOfStudent.add(temp);
						
					}
					
					String sql1 = SQLQueryConstant.SET_GENERATED_REPORT_CARD_TRUE;
					statement = connection.prepareStatement(sql1);
					statement.setString(1, studentid);
					int row = statement.executeUpdate();
						
					
				}catch(SQLException se) {
					
					logger.error(se.getMessage());
					
				}
		
		return CoursesOfStudent;
	}

}
