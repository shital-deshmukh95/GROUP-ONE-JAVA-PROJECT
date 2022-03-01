package com.crs.lt.business;

import java.util.List;

import org.apache.log4j.Logger;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.Course;
import com.crs.lt.bean.Professor;
import com.crs.lt.bean.RegisteredCourse;
import com.crs.lt.bean.Student;
import com.crs.lt.doa.AdminDOAInterface;
import com.crs.lt.doa.AdminDoaOperation;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;

public class AdminService implements AdminInterface {
	private static Logger logger =Logger.getLogger(AdminDoaOperation.class);

	AdminDOAInterface adminDOAInterface = new AdminDoaOperation();
	@Override
	public List<Catalog> viewCourses() {
		// TODO Auto-generated method stub
		return adminDOAInterface.viewCourses(); 
	}

	@Override
	public void addProfessor(Professor professor) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void removeCourse(String coursecode, List<Catalog> courseList) throws CourseNotFoundException, CourseNotDeletedException {
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
	public void addCourse(Catalog course, List<Catalog> courseList) throws CourseExistsAlreadyException {
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
			if(course.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
				return false; 
			}
		}
		return true;
	}

	@Override
	public void setGeneratedReportCardTrue(String Studentid) {
		// TODO Auto-generated method stub
		adminDOAInterface.setGeneratedReportCardTrue(Studentid);
		
	}

	@Override
	public List<RegisteredCourse> generateGradeCard(String Studentid) {
		// TODO Auto-generated method stub
		return adminDOAInterface.generateGradeCard(Studentid);
	}
	
	
}
