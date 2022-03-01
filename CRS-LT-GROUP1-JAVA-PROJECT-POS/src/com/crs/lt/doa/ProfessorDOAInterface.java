package com.crs.lt.doa;

import java.sql.SQLException;
import java.util.List;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.EnrolledStudent;
import com.crs.lt.exceptions.GradeNotAddedException;

/**
 * @author user203
 *
 */
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
	 */
	public Boolean addGrade(String studentId,String courseCode,String grade) throws GradeNotAddedException;
	public String getProfessorById(String profId);
}
