package com.crs.lt.application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.Grade;
import com.crs.lt.business.NotificationInterface;
import com.crs.lt.business.NotificationService;
import com.crs.lt.business.RegistrationInterface;
import com.crs.lt.business.RegistrationService;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.SeatNotAvailableException;

/**
 * @author user203
 *
 */
public class StudentCRSApplication {
	
	Boolean is_registered;
	RegistrationInterface registrationInterface = new RegistrationService();
	NotificationInterface notificationInterface = new NotificationService();
	Scanner sc = new Scanner(System.in);
	public void studentMenu(String studentId) throws SQLException, CourseNotFoundException, SeatNotAvailableException{
		while(CRSApplication.verified){
			System.out.println(CRSApplication.ANSI_GREEN+"---------------STUDENT MENU------------------"+CRSApplication.ANSI_RESET);
			//System.out.println("**************************************************"+CRSApplication.ANSI_RESET);
			System.out.println(CRSApplication.ANSI_PURPLE+"1. Course Registration");
			System.out.println("2. Add Course for Student");
			System.out.println("3. Drop Course for Student");
			System.out.println("4. View Available Course List");
			System.out.println("5. View Registered Courses");
			System.out.println("6. View grade card");
			System.out.println("7. Make Payment");
			System.out.println("8. Logout"+CRSApplication.ANSI_RESET);
			
			int choice = sc.nextInt();
			
			switch (choice) {
			
			case 1: 
				registerCourses(studentId);
				break;
				
			case 2:
				addCourse(studentId);
				break;
				
			case 3:
				dropCourse(studentId);
				break;
				
			case 4:
				viewCourse(studentId);
				break;
				
			case 5:
				viewRegisteredCourse(studentId);
				break;
				
			case 6:
				viewGradeCard(studentId);
				break;
				
			case 7:
				makePayment(studentId);
				break;
				
			case 8:
				CRSApplication.verified = false;
				break;			
				
			default:
				System.out.println("Enter a valid choice");

		
		}
	}
		}
		
	private void makePayment(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		double fee = 0;
		boolean isRegistered = false;
		boolean isPaid = false;
		try {
			isRegistered = registrationInterface.getRegistrationStatus(studentId);
			isPaid = registrationInterface.getPaymentStatus(studentId);
			fee=registrationInterface.calculateFee(studentId);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if(!isRegistered)
		{
			System.out.println("You have not registered yet");
		}
		else if(isRegistered && !isPaid)
		{
			try {
				System.out.println("Your total fee  = " + fee);
				notificationInterface.sendNotification();
				System.out.println("Payment Successful by StudentId :" + studentId);
				registrationInterface.setPaymentStatus(studentId);	
			}
			catch (Exception e) {
				// TODO: handle exception
			System.out.println(e.getMessage());
			}
		
		}
		
	}
	private void viewGradeCard(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		List<Grade> gradeCard=null;
		boolean isReportGenerated = false;
		isReportGenerated = registrationInterface.isReportGenerated(studentId);
		if(isReportGenerated) {
			gradeCard = registrationInterface.viewGradeCard(studentId);
			
			if(gradeCard.isEmpty())
			{
				System.out.println("You haven't registered for any course");
				return;
			}
			
			for(Grade grade : gradeCard)
			{
				System.out.println("Grade Card for Student: " + studentId);
				System.out.println("Course Name: " + grade.getCourseName());
				System.out.println("Course Code: " + grade.getCourseCode());
				System.out.println("Grade: " + grade.getGrade());
			}
		}
		else
			System.out.println("No report generated");
	}
	private List<Course> viewRegisteredCourse(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		List<Course> course_registered = null;
		course_registered = registrationInterface
				.viewRegisteredCourses(studentId);

		if (course_registered.isEmpty()) {
			System.out.println("No course registered");
			return course_registered;
		}
		System.out.println(CRSApplication.ANSI_BOLD+ CRSApplication.ANSI_RED+"-------COURSE LIST----------"+CRSApplication.ANSI_RESET);
		
		System.out.println(String.format("%20s | %20s | %20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
		for(Course obj : course_registered)
		{
			System.out.println(String.format("%20s | %20s | %20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId()));
		}

		return course_registered;

	}
	private List<Course> viewCourse(String studentId) throws SQLException {
		List <Course> courseList = null;
		courseList = registrationInterface.viewCourses(studentId);
		if (!courseList.isEmpty()){
			System.out.println(String.format("%20s | %20s | %20s | %20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS"));
			for(Course obj : courseList)
			{
				System.out.println(String.format("%20s | %20s | %20s | %20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId(), obj.getAvailable_seats()));
			}
		}
		else{
			System.out.println("No courses available currently");
		}
			
		
		return courseList;
		
	}
	private void dropCourse(String studentId) throws CourseNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
			List<Course> registeredCourseList=viewRegisteredCourse(studentId);
			if(registeredCourseList==null)
				return;
			
			System.out.println("Enter the Course Code : ");
			String courseCode = sc.next();
			try
			{
				registrationInterface.dropCourse(courseCode, studentId,registeredCourseList);
				System.out.println("You have successfully dropped Course : " + courseCode);
				
			}
			catch(CourseNotFoundException e)
			{
				System.out.println("You have not registered for course : " + e.getCourseCode());
			} 
			catch (SQLException e) 
			{

				System.out.println(e.getMessage());
			}
		
		
	}
	private void addCourse(String studentId) throws CourseNotFoundException, SQLException, SeatNotAvailableException {
		// TODO Auto-generated method stub
			List<Course> availableCourseList=viewCourse(studentId);
			
			if(availableCourseList==null)
				return;

			try
			{
				System.out.println("Enter Course Code : " );
				String courseCode = sc.next();
				if(registrationInterface.addCourse(courseCode, studentId,availableCourseList))
				{
					System.out.println(" You have successfully registered for Course : " + courseCode);
				}
				else
				{
					System.out.println(" You have already registered for Course : " + courseCode);
				}
			}
			 catch(SQLException e)
			{
				System.out.println(e.getMessage());
			} catch (CourseNotFoundException e) {
				System.out.println(e.getMessage());
				
			}  catch (SeatNotAvailableException e) {
				System.out.println(e.getMessage());
				
			}
	}
	private void registerCourses(String studentId) throws SQLException, SeatNotAvailableException {
		System.out.println(CRSApplication.ANSI_BOLD+ CRSApplication.ANSI_RED+"-------COURSE LIST----------"+CRSApplication.ANSI_RESET);
		// TODO Auto-generated method stub
		int count = 0;
		
			try
			{
				List<Course> courseList=viewCourse(studentId);
				
				if(courseList==null)
					return;
				
				System.out.println("Enter Course Code : ");
				String courseCode = sc.next();
				System.out.println(CRSApplication.ANSI_BOLD+ CRSApplication.ANSI_RED+"-------COURSE LIST----------"+CRSApplication.ANSI_RESET);
				if(registrationInterface.addCourse(courseCode,studentId,courseList))
				{
					System.out.println("Course " + courseCode + " registered sucessfully.");
					count++;
					System.out.println("\n*******************************************************");
					System.out.println("Registration Successful");
					System.out.println("*******************************************************\n");
					
					registrationInterface.setRegistrationStatus(studentId);
				    is_registered = true;
				}
				else
				{
					System.out.println(" You have already registered for Course : " + courseCode);
				}
			}	
			 catch (CourseNotFoundException| SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			catch (SeatNotAvailableException ex){
				System.out.println(ex.getMessage());
			}
  
		
	}
	private boolean getRegistrationStatus(String studentId) throws SQLException{
		try 
		{
			return registrationInterface.getRegistrationStatus(studentId);
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}
}
