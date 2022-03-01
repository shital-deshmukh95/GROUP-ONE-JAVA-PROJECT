package com.crs.lt.application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.EnrolledStudent;
import com.crs.lt.business.ProfessorInterface;
import com.crs.lt.business.ProfessorService;
import com.crs.lt.exceptions.GradeNotAddedException;

/**
 * @author user203
 *
 */
public class ProfessorCRSApplication {

	ProfessorInterface professorInterface = new ProfessorService();
	public void createMenu(String profID) throws SQLException, GradeNotAddedException {
		Scanner in = new Scanner(System.in);
		
		int input;
		while (CRSApplication.verified) {
			System.out.println(CRSApplication.ANSI_GREEN+"---------------PROFESSOR MENU------------------"+CRSApplication.ANSI_RESET);
			System.out.println(CRSApplication.ANSI_PURPLE+"1. view Enrolled Students");
			System.out.println("2. add Grades");
			System.out.println("3. logout"+CRSApplication.ANSI_RESET);
			
			input = in.nextInt();
			switch (input) {
			
			case 1:
				viewEnrolledStudents(profID);
				break;
			case 2:
				addGrade(profID);
				break;
			case 3:
				CRSApplication.verified = false;
				return;
			default:
				System.out.println("Please select appropriate option...");
			}
		}
		in.close();
	}

	private void addGrade(String profId) throws SQLException, GradeNotAddedException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		String courseCode, grade, studentId;
		try {
			List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
			enrolledStudents = professorInterface.viewEnrolledStudents(profId);
			System.out.println(CRSApplication.ANSI_BOLD+ CRSApplication.ANSI_RED+"Enrolled Student List"+CRSApplication.ANSI_RESET);
			System.out.println(String.format("%20s | %20s | %20s","COURSE CODE","COURSE NAME","Student ID" ));
			for (EnrolledStudent obj: enrolledStudents) {
				System.out.println(String.format("%20s | %20s | %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
			}
			List<Course> coursesEnrolled = new ArrayList<Course>();
			coursesEnrolled	= professorInterface.viewCourses(profId);
			System.out.println("----------------Add Grade--------------");
			System.out.printf("Enter student id: ");
			studentId = in.nextLine();
			System.out.printf("Enter course code: ");
			courseCode = in.nextLine();
			System.out.println("Enter grade: ");
			grade = in.nextLine();
			if ((isValidStudent(enrolledStudents, studentId))) {
						boolean gradeAdded = professorInterface.addGrade(studentId, courseCode, grade);
						if (gradeAdded){
							System.out.println("Grade added successfully for "+studentId);
						}
						else {
							System.out.println("Something wrong");
						}
					} else {
						System.out.println("Invalid data entered, try again!");
					}
			}
		catch (GradeNotAddedException e){
			System.out.println(e.getMessage());
		}
			
		
	}

	

	private boolean isValidCourse(List<Course> coursesEnrolled,
			String courseCode) {
		// check if course is valid
		boolean result = false;
		for (int i = 0; i < coursesEnrolled.size(); i++) {
			if (coursesEnrolled.get(i).getCourseCode()
					.equalsIgnoreCase(courseCode))
				result = true;
		}
		return result;
	}

	public boolean isValidStudent(List<EnrolledStudent> enrolledStudents,
			String studentId) {
		boolean result = false;
		// check if student exist in the students list
		for (int i = 0; i < enrolledStudents.size(); i++) {
			// role.equalsIgnoreCase("ADMIN")
			if (enrolledStudents.get(i).getStudentId() == studentId)
				result = true;

		}
		return result;
	}

	

	

	private void viewEnrolledStudents(String profID) throws SQLException {
		// TODO Auto-generated method stub
		try {
			System.out.println(CRSApplication.ANSI_BOLD+ CRSApplication.ANSI_RED+"Enrolled Student List"+CRSApplication.ANSI_RESET);
			System.out.println(String.format("%20s | %20s | %20s","COURSE CODE","COURSE NAME","Student" ));
			List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
			enrolledStudents = professorInterface.viewEnrolledStudents(profID);
			for (EnrolledStudent obj: enrolledStudents) {
				System.out.println(String.format("%20s | %20s | %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage()+"Something went wrong, please try again later!");
		}
		
		
	}

//	private void getCourses(String profID) {
//		// TODO Auto-generated method stub
//		
//	}
}
