package com.crs.lt.doa;

import java.util.List;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.Course;
import com.crs.lt.bean.Professor;
import com.crs.lt.bean.RegisteredCourse;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;

/**
 * @author user203
 *
 */
public interface AdminDOAInterface {
	/**
	 * @return
	 */
	

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
	public void removeCourse(String coursecode) throws CourseNotDeletedException, CourseNotFoundException;
	
	/**
	 * Method to add Course from Course Catalog
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseExistsAlreadyException  
	 */
	public void addCourse(Catalog course) throws CourseExistsAlreadyException;
	public void setGeneratedReportCardTrue(String studentid);
	public List<RegisteredCourse> generateGradeCard(String studentid);
	
	
}
