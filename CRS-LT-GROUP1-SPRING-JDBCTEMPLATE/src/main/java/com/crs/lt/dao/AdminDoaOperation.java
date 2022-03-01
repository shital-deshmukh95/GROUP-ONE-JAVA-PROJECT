package com.crs.lt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.crs.lt.mapper.CourseMapper;
import com.crs.lt.mapper.RegisteredCourseMapper;
import com.crs.lt.mapper.StudentMapper;


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
		String instructorId = professor.getProfessorID();
		String department = professor.getDepartment();
		String designation = professor.getDesignation();
		int row = jdbcTemplate.update(SQLQueryConstant.ADD_PROFESSOR_QUERY, instructorId, department, designation);
			logger.info(row + " professor added.");
			if(row == 0) {
				logger.error("Professor with professorId: " + professor.getUserId() + " not added.");
			}
			
			logger.info("Professor with professorId: " + professor.getUserId() + " added."); 
	}

	@Override
	public void removeCourse(String coursecode)
			throws CourseNotDeletedException, CourseNotFoundException, SQLException {
		int row = jdbcTemplate.update(SQLQueryConstant.DELETE_COURSE_FROM_CATALOG, coursecode);
		logger.info(row + " entries deleted.");
		if (row == 0) {
			logger.info(coursecode + " not in catalog!");

		}

		logger.info("Course with courseCode: " + coursecode + " deleted.");

	}
		
	

	@Override
	public void addCourse(Catalog course) throws CourseExistsAlreadyException, SQLException{
		// TODO Auto-generated method stub
		String courseCode = course.getCourseCode();
		String courseName = course.getCourseName();
		String description = course.getDescription();
		jdbcTemplate.update(SQLQueryConstant.ADD_COURSE_CATALOG, courseCode,courseName, description);
		
			logger.info("Course with courseCode: " + course.getCourseCode() + " is added "); 
	}

	@Override
	public void setGeneratedReportCardTrue(String studentid) throws SQLException {
		String sql = SQLQueryConstant.SET_GENERATED_REPORT_CARD_TRUE;
		int rows = jdbcTemplate.update(sql, studentid);
		
	}

	@Override
	public List<RegisteredCourse> generateGradeCard(String studentid) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = configurationJDBC.dataSource().getConnection();		List<RegisteredCourse> CoursesOfStudent = new ArrayList<RegisteredCourse>();
		List<Map<String, Object>> rows=  jdbcTemplate.queryForList(SQLQueryConstant.VIEW_REGISTRATION_COURSE, studentid);
		for (Map row : rows) {
			Course course = new Course();
			RegisteredCourse temp = new RegisteredCourse();
			course.setCourseName((String) row.get("courseName"));
			course.setInstructorId((String) row.get("instructorId"));
			course.setAvailable_seats((int) row.get("availableSeats"));

			temp.setCourse(course);
			System.out.println("course object generated");
			temp.setStudentId(studentid);
			temp.setGrade((String) row.get("grade"));
			System.out.println("graded");
			CoursesOfStudent.add(temp);
			
		}
		
		
			String sql1 = SQLQueryConstant.SET_GENERATED_REPORT_CARD_TRUE;
			int row = jdbcTemplate.update(sql1, studentid);
			if (row > 0) {
				logger.info("Grades added for studentId: " +studentid);
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
		List<Student> studentList = new ArrayList<>();
		List<Map<String, Object>> rows=  jdbcTemplate.queryForList(SQLQueryConstant.VIEW_PENDING_ADMISSION_QUERY);
		for (Map row : rows) {
			Student obj = new Student();

			obj.setUserId((String) row.get("userId"));
			obj.setStudentId((String) row.get("studentId"));
			obj.setName((String) row.get("name"));
            obj.setPassword((String) row.get("password"));
            obj.setRole(RoleConstant.stringToName((String) row.get("role")));
            //obj.setRole(RoleConstant.stringToName(row.getString("role")));
            obj.setGender((GenderConstant.stringToGender((String) row.get("gender"))));
            obj.setAddress((String) row.get("address"));
            studentList.add(obj);
        }
			logger.info(studentList.size() + " students have pending-approval.");
		return studentList;
	}

	@Override
	public void approveStudent(String studentid) throws SQLException {
		jdbcTemplate.update(SQLQueryConstant.APPROVE_STUDENT_QUERY, studentid);
		
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
		int rowAffected = jdbcTemplate.update(SQLQueryConstant.APPROVE_STUDENT_QUERY, studentid);
		if (rowAffected > 0) {
			return true;
		} else
			return false;
	}

}
