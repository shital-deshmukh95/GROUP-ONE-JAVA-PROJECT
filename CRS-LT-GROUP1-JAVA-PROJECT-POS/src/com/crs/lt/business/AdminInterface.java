package com.crs.lt.business;

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
	 */
	public List<Catalog> viewCourses();
	/**
	 * Method to ADD professor from Course Catalog
	 */
	public void addProfessor(Professor professor);
	/**
	 * Method to Delete Course from Course Catalog
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException 
	 * @throws CourseNotDeletedException 
	 */
	public void removeCourse(String coursecode, List<Catalog> courseList) throws CourseNotFoundException, CourseNotDeletedException;
	/**
	 * Method to add Course from Course Catalog
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseExistsAlreadyException  
	 */
	public void addCourse(Catalog course, List<Catalog> courseList) throws CourseExistsAlreadyException;
	public void setGeneratedReportCardTrue(String Studentid);
	/**
	 * Method to generate grade card of a Student 
	 * studentid 
	 * @return 
	 */
	public List<RegisteredCourse> generateGradeCard(String Studentid);
}
