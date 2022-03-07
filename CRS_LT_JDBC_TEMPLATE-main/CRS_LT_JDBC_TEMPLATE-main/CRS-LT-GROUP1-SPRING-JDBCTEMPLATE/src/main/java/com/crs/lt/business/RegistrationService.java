package com.crs.lt.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.Grade;
import com.crs.lt.dao.RegistrationDAOInterface;
import com.crs.lt.dao.RegistrationDaoOperation;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.SeatNotAvailableException;

@Service
public class RegistrationService implements RegistrationInterface {
	private static Logger logger =Logger.getLogger(RegistrationService.class);
	

	@Autowired
	RegistrationDaoOperation registrationDAOInterface;
	//RegistrationDAOInterface registrationDAOInterface = new RegistrationDaoOperation();
	@Override
	public boolean addCourse(String courseCode, String studentId,
			List<Course> availableCourseList) throws CourseNotFoundException, SQLException, SeatNotAvailableException {
		// TODO Auto-generated method stub
		
	

		
		if (!registrationDAOInterface.seatAvailable(courseCode)) 
		{
			throw new SeatNotAvailableException(courseCode);
		} 
		else if(!isCourseValid(courseCode, availableCourseList))
		{
			throw new CourseNotFoundException(courseCode);
		}
		
		  

		return registrationDAOInterface.addCourse(courseCode, studentId);
	}

	public boolean isCourseValid (String courseCode,List<Course>availableCourseList){

	return availableCourseList.stream().anyMatch(c -> c.getCourseCode().equalsIgnoreCase(courseCode));

		
	}
	
	@Override
	public void setRegistrationStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		registrationDAOInterface.setRegistrationStatus(studentId);
		
	}

	@Override
	public boolean getRegistrationStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDAOInterface.getRegistrationStatus(studentId);
	}

	@Override
	public boolean getPaymentStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDAOInterface.getPaymentStatus(studentId);
	}

	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDAOInterface.viewRegisteredCourses(studentId);
	}

	@Override
	public List<Course> viewCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDAOInterface.viewCourses(studentId);
	}

	@Override
	public List<Grade> viewGradeCard(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDAOInterface.viewGradeCard(studentId);
	}

	@Override
	public double calculateFee(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDAOInterface.calculateFee(studentId);
	}

	@Override
	public boolean dropCourse(String courseCode, String studentId,
			List<Course> registeredCourseList) throws CourseNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 if(!isRegistered(courseCode, studentId, registeredCourseList))
	        {
	        	throw new CourseNotFoundException(courseCode);
	        }
		
		return registrationDAOInterface.dropCourse(courseCode, studentId, registeredCourseList);
	}

	public boolean isRegistered(String courseCode,String studentId,List<Course>registeredCourseList) throws CourseNotFoundException
	{

		return registeredCourseList.stream().anyMatch(c -> c.getCourseCode().equalsIgnoreCase(courseCode));
	}
	@Override
	public boolean isReportGenerated(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDAOInterface.isReportGenerated(studentId);
	}

	@Override
	public void setPaymentStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		registrationDAOInterface.setPaymentStatus(studentId);
	}

	
}
