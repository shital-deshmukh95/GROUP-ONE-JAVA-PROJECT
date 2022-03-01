package com.crs.lt.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.EnrolledStudent;
import com.crs.lt.doa.ProfessorDOAInterface;
import com.crs.lt.doa.ProfessorDaoOperation;
import com.crs.lt.exceptions.GradeNotAddedException;

public class ProfessorService implements ProfessorInterface{
	private static Logger logger =Logger.getLogger(ProfessorService.class);

	ProfessorDOAInterface professorDOAInterface = new ProfessorDaoOperation();
	@Override
	public boolean addGrade(String studentID, String courseID, String grade)throws GradeNotAddedException {
		// TODO Auto-generated method stub
		try
		{
			professorDOAInterface.addGrade(studentID, courseID, grade);
		}
		catch(Exception ex)
		{
			throw new GradeNotAddedException(studentID);
		}
		return true;
	}

	@Override
	public List<Course> viewCourses(String profID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProfessorById(String profId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException {
		// TODO Auto-generated method stub
		List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
		return enrolledStudents=professorDOAInterface.getEnrolledStudents(profId);
	}

	
}
