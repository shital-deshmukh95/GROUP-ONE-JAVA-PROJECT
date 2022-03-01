package com.crs.lt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.Grade;
import com.crs.lt.bean.Payment;
import com.crs.lt.bean.RegisteredCourse;
import com.crs.lt.bean.Student;
import com.crs.lt.bean.User;
import com.crs.lt.configuration.ConfigurationJDBC;
import com.crs.lt.constant.SQLQueryConstant;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.SeatNotAvailableException;
import com.crs.lt.mapper.CourseMapper;
import com.crs.lt.mapper.GradeMapper;
import com.crs.lt.mapper.RegisteredCourseMapper;
import com.crs.lt.mapper.StudentMapper;




/**
 * @author user203
 *
 */
@Repository
public class RegistrationDaoOperation implements RegistrationDAOInterface {
	private static Logger logger=Logger.getLogger(RegistrationDaoOperation.class);

	@Autowired
	private ConfigurationJDBC configurationJDBC;

	private PreparedStatement stmt = null;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	/* Method to add course for student 
	 * @param courseCode: Course code
	 * @param studentId: Student ID
	 */
	@Override
	@Transactional
	public boolean addCourse(String courseCode, String studentId) throws CourseNotFoundException, SQLException, SeatNotAvailableException {
		String grade = "-";
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0");
		int cousreAdded =  jdbcTemplate.update(SQLQueryConstant.ADD_COURSE_FOR_STUDENT,studentId, courseCode,grade);
		System.out.println(cousreAdded);
		if(cousreAdded != 0 && cousreAdded > 0 ) {
			jdbcTemplate.update(SQLQueryConstant.DECREMENT_AVAILABLE_SEATS, courseCode);
			jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1");
			return true;
		}
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1");
		return false;
		
	}

	/* Method to add course for student 
	 *
	 * @param studentId: Student ID
	 */
	@Override
	public void setRegistrationStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		jdbcTemplate.update(SQLQueryConstant.SET_REGISTRATION_STATUS, studentId);
		
	}

	/* Method to add course for student 
	 * 
	 * @param studentId: Student ID
	 * return status
	 */
	@Override
	public boolean getRegistrationStatus(String studentId) throws SQLException {
	
		Boolean student = jdbcTemplate.queryForObject(SQLQueryConstant.GET_REGISTRATION_STATUS, new Object[] {studentId}, Boolean.class);
		System.out.println("getRegistrationStatus: " +student);
		
		return student;
	}

	/* Method to add course for student 
	 * 
	 * @param studentId: Student ID
	 * return boolean
	 */
	@Override
	public boolean getPaymentStatus(String studentId) throws SQLException {
		Boolean student = jdbcTemplate.queryForObject(SQLQueryConstant.GET_PAYMENT_STATUS, new Object[] {studentId}, Boolean.class);
		System.out.println("getPaymentStatus: " +student);
		
		return student;

	}

	/* Method to view registered courses
	 *
	 * @param studentId: Student ID
	 * return list of registered course
	 */
	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		

		
		List<Course> registeredCourseList = new ArrayList<>();
		registeredCourseList = jdbcTemplate.query(SQLQueryConstant.VIEW_REGISTRATION_COURSE, new Object[]{studentId},  new CourseMapper());
		

		return registeredCourseList;
	}
	
	/* Method to view course
	 *
	 * @param studentId: Student ID
	 * return list of available courses
	 */

	//@SuppressWarnings("deprecation")
	@Override
	public List<Course> viewCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		List<Course> availableCourseList = new ArrayList<>();
		availableCourseList = jdbcTemplate.query(SQLQueryConstant.VIEW_COURSE, new Object[]{studentId},  new CourseMapper());
		return availableCourseList;
		
	}

	/* Method to view grade card 
	 * 

	 * @param studentId: Student ID
	 * return list of grade card of the student
	 */

	@Override
	public List<Grade> viewGradeCard(String studentId) throws SQLException {

		
		List<Grade> grade_List = new ArrayList<Grade>();
		grade_List = jdbcTemplate.query(SQLQueryConstant.VIEW_GRADE, new Object[] {studentId}, new GradeMapper());
		
		
		return grade_List;
	}

	@Override
	public double calculateFee(String studentId) throws SQLException {
		double fee = 0;
		fee = jdbcTemplate.queryForObject(SQLQueryConstant.CALCULATE_FEES, new Object[] {studentId}, Double.class);
		return fee;
	}

	@Override
	public boolean dropCourse(String courseCode, String studentId,
			List<Course> registeredCourseList) throws SQLException {
		boolean courseDropped;
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0");
		int cousreAdded =  jdbcTemplate.update(SQLQueryConstant.DROP_COURSE,courseCode, studentId);
		System.out.println(cousreAdded);
		if(cousreAdded != 0 && cousreAdded > 0 ) {
			jdbcTemplate.update(SQLQueryConstant.INCREMENT_AVAILABLE_SEATS, courseCode);
			jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1");
			return true;
		}
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1");
		return false;
	
	}

	@Override
	public boolean isReportGenerated(String studentId) throws SQLException {

		Boolean student = jdbcTemplate.queryForObject(SQLQueryConstant.GET_GENERATED_REPORT_CARD_TRUE,
				new Object[] { studentId }, Boolean.class);

		return student;

	}

	@Override
	public void setPaymentStatus(String studentId) throws SQLException {
		jdbcTemplate.update(SQLQueryConstant.SET_PAYMENT_STATUS, studentId);
		
	}

	@Override
	public boolean seatAvailable(String courseCode)throws SQLException, SeatNotAvailableException {
		// TODO Auto-generated method stub
		System.out.println(courseCode);
		Boolean seats = jdbcTemplate.queryForObject(SQLQueryConstant.GET_SEATS, new Object[] {courseCode}, Boolean.class);
		return seats;
	}

}
