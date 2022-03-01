package com.crs.lt.application;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.Course;
import com.crs.lt.bean.Grade;
import com.crs.lt.business.AdminInterface;
import com.crs.lt.business.AdminService;
import com.crs.lt.business.NotificationInterface;
import com.crs.lt.business.NotificationService;
import com.crs.lt.business.RegistrationInterface;
import com.crs.lt.business.RegistrationService;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;

/**
 * @author user203
 *
 */
public class AdminCRSApplication {
	RegistrationInterface registrationInterface = new RegistrationService();
	NotificationInterface notificationInterface = new NotificationService();
	AdminInterface adminInterface = new AdminService();
	Scanner in = new Scanner(System.in);
	public void createMenu() throws CourseNotFoundException, CourseNotDeletedException{
		while(CRSApplication.verified) {
			System.out.println(CRSApplication.ANSI_GREEN+"---------------ADMIN MENU------------------"+CRSApplication.ANSI_RESET);
			System.out.println(CRSApplication.ANSI_PURPLE+"1. View course");
			System.out.println("2. Add Course to catalog");
			System.out.println("3. Delete Course from catalog");
			System.out.println("4. Generate Report Card");
			System.out.println("5. Logout");
			System.out.println("*****************************"+CRSApplication.ANSI_RESET);
			
			int choice = in.nextInt();
			
			switch(choice) {
			case 1:
				viewCourses();
				break;
				
			case 2:
				addCourse();
				break;
				
			case 3:
				removeCourse();
				break;
			case 4:
				generateReportCard();
			case 5:
				CRSApplication.verified = false;
				return;

			default:
				System.out.println("***** Wrong Choice *****");
			}
		}

	}

	private void generateReportCard() {
		// TODO Auto-generated method stub

		List<Grade> grade_card=null;
		boolean isReportGenerated = true;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("\nEnter the StudentId for report card generation : ");
		String studentId = in.next();
		
		try 
		{
			adminInterface.setGeneratedReportCardTrue(studentId);
			if(isReportGenerated) {
				grade_card = registrationInterface.viewGradeCard(studentId);
				System.out.println(String.format("%20s | %20s | %20s","COURSE CODE", "COURSE NAME", "GRADE"));
				
				if(grade_card.isEmpty())
				{
					System.out.println("You haven't registered for any course");
					return;
				}
				
				for(Grade obj : grade_card)
				{
					System.out.println(String.format("%20s | %20s | %20s",obj.getCourseCode(), obj.getCourseName(),obj.getGrade()));
				}
			}
			else
				System.out.println("Report card not yet generated");
		} 
		catch (SQLException e) 
		{

			System.out.println(e.getMessage());
		}
	}

	private void removeCourse() throws CourseNotFoundException, CourseNotDeletedException {
		// TODO Auto-generated method stub
		List<Catalog> courseList = viewCourses();
		System.out.println("Enter Course Code:");
		String courseCode = in.next();

		try {
			adminInterface.removeCourse(courseCode, courseList);
			System.out.println("\nCourse Deleted : "+courseCode+"\n");
		} catch (CourseNotFoundException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		catch (CourseNotDeletedException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
			
	}
	private void addCourse() {
		List<Catalog> courseList = viewCourses();

		in.nextLine();
		System.out.println("Enter Course Code:");
		String courseCode = in.nextLine();
		
		System.out.println("Enter Course Name:");
		String courseName = in.next();
		System.out.println("Enter decription of course");
		String description = in.next();
		
		
		Catalog course = new Catalog(courseCode, courseName, description);
		course.setCourseCode(courseCode);
		course.setCourseName(courseName);
		
		
		try {
		adminInterface.addCourse(course, courseList);		
		}
		catch (CourseExistsAlreadyException e) {
			System.out.println("Course already existed "+e.getMessage());
		}
		
	}
	private List<Catalog> viewCourses() {
		List<Catalog> courseList = adminInterface.viewCourses();
		if(courseList.size() == 0) {
			System.out.println("Nothing present in the catalogue!");
			return courseList;
		}
		System.out.println(String.format("%20s | %20s | %20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR ID"));
		for(Catalog course : courseList) {
			System.out.println(String.format("%20s | %20s | %20s", course.getCourseCode(), course.getCourseName(), course.getDescription()));
		}
		return courseList;
	}
	}
