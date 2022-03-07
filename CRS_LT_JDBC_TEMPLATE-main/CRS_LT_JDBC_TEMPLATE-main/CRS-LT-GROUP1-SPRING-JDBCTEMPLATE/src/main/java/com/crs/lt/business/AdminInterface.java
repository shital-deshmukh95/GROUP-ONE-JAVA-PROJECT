package com.crs.lt.business;

import java.sql.SQLException;
import java.util.List;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.Course;
import com.crs.lt.bean.Professor;
import com.crs.lt.bean.RegisteredCourse;
import com.crs.lt.bean.Student;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;

/**
 * @author user203
 *
 */
public interface AdminInterface {

	/**
	 * Method to List Course from Course Catalog
	 * @throws SQLException 
	 */
	public List<Catalog> viewCourses() throws SQLException;
	/**
	 * Method to ADD professor from Course Catalog
	 * @throws SQLException 
	 */
	public void addProfessor(Professor professor) throws SQLException;
	/**
	 * Method to Delete Course from Course Catalog
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException 
	 * @throws CourseNotDeletedException 
	 * @throws SQLException 
	 */
	public void removeCourse(String coursecode, List<Catalog> courseList) throws CourseNotFoundException, CourseNotDeletedException, SQLException;
	/**
	 * Method to add Course from Course Catalog
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseExistsAlreadyException  
	 * @throws SQLException 
	 */
	public void addCourse(Catalog course, List<Catalog> courseList) throws CourseExistsAlreadyException, SQLException;
	public void setGeneratedReportCardTrue(String Studentid) throws SQLException;
	/**
	 * Method to generate grade card of a Student 
	 * studentid 
	 * @return 
	 * @throws SQLException 
	 */
	public List<RegisteredCourse> generateGradeCard(String Studentid) throws SQLException;
	
	
	
	
	/**
	 * View professor in the institute
	 * @return List of the professors in the institute  
	 */
	public List<Professor> viewProfessors();
	
	/**
	 * Method to view Students yet to be approved by Admin
	 * @return List of Students with pending admissions
	 * @throws SQLException 
	 */
	public List<Student> viewPendingAdmissions() throws SQLException;
	
	
	
	/**
	 * Method to approve a Student 
	 * studentid
	 * studentlist
	 */
	
	public void approveStudent(String studentid, List<Student> studentlist) throws SQLException;
	
		
	/**
	 * Method to assign Course to a Professor
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException 
	 * @throws SQLException 
	 * @throws UserNotFoundException 
	 */
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, SQLException;
	
	public boolean approveStudentRequest(String studentid, List<Student> studentlist) throws SQLException;
}
