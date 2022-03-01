package com.crs.lt.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.Course;
import com.crs.lt.bean.Professor;
import com.crs.lt.bean.RegisteredCourse;
import com.crs.lt.bean.Student;
import com.crs.lt.dao.AdminDOAInterface;
import com.crs.lt.dao.AdminDoaOperation;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.validators.AdminValidator;

@Service
public class AdminService implements AdminInterface {
	private static Logger logger =Logger.getLogger(AdminDoaOperation.class);

	@Autowired
	private AdminDoaOperation adminDOAInterface;
	//AdminDOAInterface adminDOAInterface = new AdminDoaOperation();
	@Override
	public List<Catalog> viewCourses() throws SQLException {
		// TODO Auto-generated method stub
		return adminDOAInterface.viewCourses(); 
	}

	/**
	 * @param professor : Professor Object storing details of a professor
	 * @throws ProfessorNotAddedException
	 */
	@Override
	public void addProfessor(Professor professor) throws SQLException {
		adminDOAInterface.addProfessor(professor);
		
	}



	@Override
	public void removeCourse(String coursecode, List<Catalog> courseList) throws CourseNotFoundException, CourseNotDeletedException, SQLException {
		// TODO Auto-generated method stub
		if(!isValidCourse(coursecode, courseList)){
			logger.info("Course Code" + coursecode + "not found");
			throw new CourseNotFoundException(coursecode);
		}
		try {
			adminDOAInterface.removeCourse(coursecode);
		}
		catch(CourseNotDeletedException | CourseNotFoundException e){
			throw e;
		}
		
		
	}
	/**
	 * Method to validate if dropCourse is already present in catalog
	 * @param dropCourseCode
	 * @param courseList
	 * @return if dropCourse is already present in catalog
	 */
	public boolean isValidCourse(String dropCourseCode, List<Catalog> courseList) {
		for(Catalog course : courseList) {
			if(dropCourseCode.equalsIgnoreCase(course.getCourseCode())) {
				return true; 
			}
		}
		return false;
	}

	@Override
	public void addCourse(Catalog course, List<Catalog> courseList) throws CourseExistsAlreadyException, SQLException {
		// TODO Auto-generated method stub
		try {
			if(!isValidAddCourse(course, courseList)) {
				System.out.println("courseCode: " + course.getCourseCode() + " already present");
				throw new CourseExistsAlreadyException(course.getCourseCode());
			}
			adminDOAInterface.addCourse(course);
		}
		catch(CourseExistsAlreadyException e) {
			throw e;
		}
		
	}

	private boolean isValidAddCourse(Catalog course, List<Catalog> courseList) {
		// TODO Auto-generated method stub
		for(Catalog addCourse : courseList) {
			if(addCourse.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
				return false; 
			}
		}
		return true;
	}

	@Override
	public void setGeneratedReportCardTrue(String Studentid) throws SQLException {
		// TODO Auto-generated method stub
		adminDOAInterface.setGeneratedReportCardTrue(Studentid);
		
	}

	@Override
	public List<RegisteredCourse> generateGradeCard(String Studentid) throws SQLException {
		// TODO Auto-generated method stub
		return adminDOAInterface.generateGradeCard(Studentid);
	}

	@Override
	public List<Professor> viewProfessors() {
		// TODO Auto-generated method stub
		return adminDOAInterface.viewProfessors();
	}

	@Override
	public List<Student> viewPendingAdmissions() throws SQLException {
		// TODO Auto-generated method stub
		return adminDOAInterface.viewPendingAdmissions();
	}

	@Override
	public void approveStudent(String studentid, List<Student> studentlist) throws SQLException {


		try {
			
			if(AdminValidator.isValidUnapprovedStudent(studentid, studentlist)) {
				
				throw new SQLException();
			}
			adminDOAInterface.approveStudent(studentid);
		}
		catch(SQLException e) {
			
			throw e;
		}
		
	}

	/**
	 * Method to assign Course to a Professor
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException 
	 * @throws SQLException 
	 * @throws UserNotFoundException 
	 */
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, SQLException
	{
		try {
			adminDOAInterface.assignCourse(courseCode, professorId);
		}
		catch(CourseNotFoundException  e) {
			throw e;
		}
	}

	@Override
	public boolean approveStudentRequest(String studentid, List<Student> studentlist) throws SQLException {
		// TODO Auto-generated method stub
		boolean isApproved = false;
try {
			
			if(AdminValidator.isValidUnapprovedStudent(studentid, studentlist)) {
				
				return isApproved = true;
				
			}
			adminDOAInterface.approveStudentRequest(studentid);
			return isApproved = true;
		}
		catch(SQLException e) {
			
			return isApproved = true;
		}
		
	}
	
	
}
