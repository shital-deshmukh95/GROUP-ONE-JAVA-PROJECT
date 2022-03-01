package com.crs.lt.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.Course;
import com.crs.lt.bean.Professor;
import com.crs.lt.bean.RegisteredCourse;
import com.crs.lt.bean.Student;
import com.crs.lt.bean.User;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;

/**
 * @author user203
 *
 */
//@Repository
public interface AdminDOAInterface {
	/**
	 * @return
	 */
	

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
	public void removeCourse(String coursecode) throws CourseNotDeletedException, CourseNotFoundException, SQLException;
	
	/**
	 * Method to add Course from Course Catalog
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseExistsAlreadyException  
	 * @throws SQLException 
	 */
	public void addCourse(Catalog course) throws CourseExistsAlreadyException, SQLException;
	public void setGeneratedReportCardTrue(String studentid) throws SQLException;
	public List<RegisteredCourse> generateGradeCard(String studentid) throws SQLException;
	
	
	
	
	public List<Professor> viewProfessors();
	
	
	
	/**
	 * Fetch Students yet to approved using SQL commands
	 * @return List of Students yet to approved
	 * @throws SQLException 
	 */
	public List<Student> viewPendingAdmissions() throws SQLException;
	
	/**
	 * Method to approve a Student 
	 * studentid
	 * studentlist
	 */
	
	
	public void approveStudent(String studentid) throws SQLException;
	
	public boolean approveStudentRequest(String studentid) throws SQLException;
	
	
	
	/**
	 * Method to assign Course to a Professor
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException 
	 * @throws SQLException 
	 * @throws UserNotFoundException 
	 */
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, SQLException;
	
	public void addUser(User user) throws SQLException;
	
	
}
