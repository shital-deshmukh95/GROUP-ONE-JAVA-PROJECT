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
	
	
	
	/* Method to add course for student 
	 * @param courseCode: Course code
	 * @param studentId: Student ID
	 */
	@Override
	public boolean addCourse(String courseCode, String studentId) throws CourseNotFoundException, SQLException, SeatNotAvailableException {
		// TODO Auto-generated method stub
		Connection conn = configurationJDBC.dataSource().getConnection();
		boolean courseAdded = false;

		try 
		{
			stmt = conn.prepareStatement(SQLQueryConstant.ADD_COURSE_FOR_STUDENT);
			stmt.setString(1, studentId);
			stmt.setString(2, courseCode);
			stmt.setString(3, "-");
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SQLQueryConstant.DECREMENT_AVAILABLE_SEATS);
			stmt.setString(1, courseCode);
			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e) 
		{
			logger.error("SQLException in DAO" + e.getMessage());
		}
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("SQLException in DAO" + e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("SQLException in DAO" + e.getMessage());
			}
		}
		return false;
		
		
		
	}

	/* Method to add course for student 
	 *
	 * @param studentId: Student ID
	 */
	@Override
	public void setRegistrationStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = configurationJDBC.dataSource().getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLQueryConstant.SET_REGISTRATION_STATUS);
			stmt.setString(1, studentId);
			stmt.executeUpdate();

		} 
		catch (SQLException e) 
		{
			logger.error("Exception in DAO" + e.getMessage());

		} 
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
		}
		
	}

	/* Method to add course for student 
	 * 
	 * @param studentId: Student ID
	 * return status
	 */
	@Override
	public boolean getRegistrationStatus(String studentId) throws SQLException {
	
		Connection conn = configurationJDBC.dataSource().getConnection();
		boolean status = false;
		try 
		{
			stmt = conn.prepareStatement(SQLQueryConstant.GET_REGISTRATION_STATUS);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			status = rs.getBoolean(1);	
		} 
		catch (SQLException e) 
		{
			logger.error("Exception in DAO" + e.getMessage());

		} 
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
		}

		return status;
	}

	/* Method to add course for student 
	 * 
	 * @param studentId: Student ID
	 * return boolean
	 */
	@Override
	public boolean getPaymentStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
//		boolean paymentStatus = false;
//		for (Payment payment: payments){
//			if(payment.getStud().getUserId().equals(studentId)){
//				paymentStatus = payment.isStatus();
//			}
//			paymentStatus = payment.isStatus();
//		}
//		return paymentStatus;
		{
			Connection conn = configurationJDBC.dataSource().getConnection();
			boolean status = false;
			try 
			{
				stmt = conn.prepareStatement(SQLQueryConstant.GET_PAYMENT_STATUS);
				stmt.setString(1, studentId);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				status = rs.getBoolean(1);
				logger.info(status);	
			} 
			catch (SQLException e) 
			{
				logger.error("Exception in DAO" + e.getMessage());

			} 
			finally
			{
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error("Exception in DAO" + e.getMessage());
				}
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error("Exception in DAO" + e.getMessage());
				}
			}

			return status;
	}

	}

	/* Method to view registered courses
	 *
	 * @param studentId: Student ID
	 * return list of registered course
	 */
	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		

		Connection conn = configurationJDBC.dataSource().getConnection();
		List<Course> registeredCourseList = new ArrayList<>();
		try {
			stmt = conn
					.prepareStatement(SQLQueryConstant.VIEW_REGISTRATION_COURSE);
			stmt.setString(1, studentId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				registeredCourseList.add(new Course(rs.getString("courseCode"),
						rs.getString("courseName"),
						rs.getString("instructorId"), rs.getInt("availableSeats")));

			}
		} catch (SQLException e) {
			logger.error("Exception in DAO" + e.getMessage());

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
		}

		return registeredCourseList;
	}
	
	/* Method to view course
	 *
	 * @param studentId: Student ID
	 * return list of available courses
	 */

	@Override
	public List<Course> viewCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		List<Course> availableCourseList = new ArrayList<>();
		
		
		Connection conn = configurationJDBC.dataSource().getConnection();
		
		try 
		{
			stmt = conn.prepareStatement(SQLQueryConstant.VIEW_COURSE);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				availableCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
						rs.getString("instructorId"), rs.getInt("availableSeats")));

			}
			

		} 
		catch (SQLException e) 
		{
			logger.error("Exception in DAO" + e.getMessage());
		} 
		catch (Exception e)
		{
			logger.error("Exception in DAO" + e.getMessage());
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
			
		}
		
		return availableCourseList;
		
	}

	/* Method to view grade card 
	 * 

	 * @param studentId: Student ID
	 * return list of grade card of the student
	 */

	@Override
	public List<Grade> viewGradeCard(String studentId) throws SQLException {

		Connection conn = configurationJDBC.dataSource().getConnection();
		List<Grade> grade_List = new ArrayList<Grade>();
		try
		{
			stmt = conn.prepareStatement(SQLQueryConstant.VIEW_GRADE);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String courseCode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				String grade = rs.getString("grade");
				Grade obj = new Grade(courseCode, courseName,grade);
				grade_List.add(obj);
			}
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
			
		}
		
		return grade_List;
	}

	@Override
	public double calculateFee(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = configurationJDBC.dataSource().getConnection();
		double fee = 0;
		try
		{
			stmt = conn.prepareStatement(SQLQueryConstant.CALCULATE_FEES);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			fee = rs.getDouble(1);
		}
		catch(SQLException e)
		{
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return fee;
	}

	@Override
	public boolean dropCourse(String courseCode, String studentId,
			List<Course> registeredCourseList) throws SQLException {
		boolean courseDropped = false;
		Connection conn = configurationJDBC.dataSource().getConnection();
		
		
		try
		{
			stmt = conn.prepareStatement(SQLQueryConstant.DROP_COURSE);
			stmt.setString(1, courseCode);
			stmt.setString(2, studentId);
			stmt.execute();
			
			stmt = conn.prepareStatement(SQLQueryConstant.INCREMENT_AVAILABLE_SEATS);
			stmt.setString(1, courseCode);
			stmt.execute();
			
			stmt.close();
			
			return true;
		}
		catch(Exception e)
		{
			logger.error("Exception in DAO" + e.getMessage());
		}
		finally
		{

			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception in DAO" + e.getMessage());
			}
		}
		
	
	return false;
	
	}

	@Override
	public boolean isReportGenerated(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		
//		for (RegisteredCourse registeredCourse: registeredCoursesList){
//			if (registeredCourse.getStudentId().equals(studentId) && !registeredCourse.getGrade().equals(null)){
//				return reportGenerated= true;
//			}
//			else 
//				reportGenerated = false;
//		}
		Connection conn = configurationJDBC.dataSource().getConnection();
		boolean status = false;
		try 
		{
			stmt = conn.prepareStatement(SQLQueryConstant.GET_GENERATED_REPORT_CARD_TRUE);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			status = rs.getBoolean(1);
			//System.out.println(status);	
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
	
		} 
		finally
		{
			stmt.close();
			conn.close();
		}
	
		return status;
	}

	@Override
	public void setPaymentStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = configurationJDBC.dataSource().getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLQueryConstant.SET_PAYMENT_STATUS);
			stmt.setString(1, studentId);
			stmt.executeUpdate();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			logger.error(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}
	}

	@Override
	public boolean seatAvailable(String courseCode)throws SQLException, SeatNotAvailableException {
		// TODO Auto-generated method stub
		Connection conn = configurationJDBC.dataSource().getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLQueryConstant.GET_SEATS);
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return (rs.getInt("availableSeats") > 0);
			}

		}
		catch (SQLException e) {
			logger.error("Exception in DAO" + e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return true;
	}

}
