package com.crs.lt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.Course;
import com.crs.lt.bean.Professor;
import com.crs.lt.bean.RegisteredCourse;
import com.crs.lt.bean.Student;
import com.crs.lt.bean.User;
import com.crs.lt.configuration.ConfigurationJDBC;
import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.mapper.CatalogMapper;


@Repository
public class AdminDoaOperation implements AdminDOAInterface {
	private static Logger logger=Logger.getLogger(AdminDoaOperation.class);

	private PreparedStatement statement = null;
	@Autowired
	private ConfigurationJDBC configurationJDBC;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Catalog> viewCourses() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Catalog> courseList = new ArrayList<>();
		courseList =  jdbcTemplate.query(SQLQueryConstant.VIEW_COURSE_CATALOG, new CatalogMapper());
		logger.info("Number of courses in the Catalog are : " + courseList.size());
		
		return courseList; 
	}

	@Override
	public void addProfessor(Professor professor) throws SQLException {
		Connection connection = configurationJDBC.dataSource().getConnection();
try {
			
			this.addUser(professor);
			
		}catch (SQLException e) {
			
			logger.error(e.getMessage());
			throw e;
			
		}
		
		
		statement = null;
		try {
			
			String sql = SQLQueryConstant.ADD_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, professor.getUserId());
			statement.setString(2, professor.getDepartment());
			statement.setString(3, professor.getDesignation());
			int row = statement.executeUpdate();

			logger.info(row + " professor added.");
			if(row == 0) {
				logger.error("Professor with professorId: " + professor.getUserId() + " not added.");
				throw new SQLException();
			}
			
			logger.info("Professor with professorId: " + professor.getUserId() + " added."); 
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			throw new SQLException();
			
		} 
		
		
	}

	@Override
	public void removeCourse(String coursecode)  throws CourseNotDeletedException, CourseNotFoundException, SQLException{
		statement = null;
		Connection connection = configurationJDBC.dataSource().getConnection();
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
	public void addCourse(Catalog course) throws CourseExistsAlreadyException, SQLException{
		// TODO Auto-generated method stub
		statement = null;
		Connection connection = configurationJDBC.dataSource().getConnection();
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
	public void setGeneratedReportCardTrue(String studentid) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();
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
	public List<RegisteredCourse> generateGradeCard(String studentid) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();
List<RegisteredCourse> CoursesOfStudent = new ArrayList<RegisteredCourse>();
		
		try {
					statement = connection.prepareStatement(SQLQueryConstant.VIEW_REGISTRATION_COURSE);
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


	public List<Professor> viewProfessors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> viewPendingAdmissions() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();
		statement = null;
		List<Student> userList = new ArrayList<Student>();
		try {
			
			String sql = SQLQueryConstant.VIEW_PENDING_ADMISSION_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				
				Student user = new Student();
				user.setUserId(resultSet.getString(1));
				user.setName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(RoleConstant.stringToName(resultSet.getString(4)));
				user.setGender(GenderConstant.stringToGender( resultSet.getString(5)));
				user.setAddress(resultSet.getString(6));
				user.setStudentId(resultSet.getString(7));
				userList.add(user);
				
			}
			
			logger.info(userList.size() + " students have pending-approval.");
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			
		}
		
		return userList;
	}

	@Override
	public void approveStudent(String studentid) throws SQLException {
		Connection connection = configurationJDBC.dataSource().getConnection();
		statement = null;
		try {
			String sql = SQLQueryConstant.APPROVE_STUDENT_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,studentid);
			int row = statement.executeUpdate();
			
			logger.info(row + " student approved.");
			if(row == 0) {
				logger.info("Student with studentId: " + studentid + " not found.");
				throw new SQLException();
			}
			
			logger.info("Student with studentId: " + studentid + " approved by admin.");
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			
		}
		
	}


	@Override
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();
		statement = null;
		try {
			String sql = SQLQueryConstant.ASSIGN_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,professorId);
			statement.setString(2,courseCode);
			int row = statement.executeUpdate();
			
			logger.info(row + " course assigned.");
			if(row == 0) {
				logger.error(courseCode + " not found");
				throw new CourseNotFoundException(courseCode);
			}
			
			logger.info("Course with courseCode: " + courseCode + " is assigned to professor with professorId: " + professorId + ".");
		
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			throw new SQLException();
			
		}
		
	}

	@Override
	public void addUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean approveStudentRequest(String studentid) throws SQLException {
		Connection connection = configurationJDBC.dataSource().getConnection();
		  try {
	            PreparedStatement ps = connection.prepareStatement(SQLQueryConstant.APPROVE_STUDENT_QUERY);
	            ps.setString(1, studentid);
	            int rowAffected = ps.executeUpdate();
	            return rowAffected == 1;
	        } catch (SQLException e) {
	            logger.info("Error: " + e.getMessage());
	        }
	        return false;
	}

}
