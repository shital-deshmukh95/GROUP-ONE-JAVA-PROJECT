package com.crs.lt.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.EnrolledStudent;
import com.crs.lt.bean.Professor;
import com.crs.lt.exceptions.GradeNotAddedException;

/**
 * @author user203
 *
 */
@Repository
public interface ProfessorDOAInterface {

	/**
	 * Method to view courses
	 * @param profId 
	 */
	public List<Course> getCoursesByProfessor(String userId);

	/**
	 * Method to view- enrolled students
	 * @param profId
	 * @throws SQLException  
	 */
	public List<EnrolledStudent> getEnrolledStudents(String CourseId) throws SQLException;
	/**
	 * Method to add grade for students
	 * @param courseCode
	 * @param studentId 
	 * @param garde
	 * @throws GradeNotAddedException  
	 * @throws SQLException 
	 */
	public Boolean addGrade(String studentId,String courseCode,String grade) throws GradeNotAddedException, SQLException;
	public String getProfessorById(String profId) throws SQLException;
	  /**
     * Method to retrieve Professor Details using userId
     *
     * @param userId Unique Id of the User
     * @return Professor object
	 * @throws SQLException 
     */
    Professor getProfessorByUserId(String userId) throws SQLException;
}
