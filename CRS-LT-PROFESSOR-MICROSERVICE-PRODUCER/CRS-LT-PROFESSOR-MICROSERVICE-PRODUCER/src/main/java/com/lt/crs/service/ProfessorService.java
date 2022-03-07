package com.lt.crs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.model.EnrolledStudent;
import com.lt.crs.model.Professor;
import com.lt.crs.model.RegisteredCourse;
import com.lt.crs.repository.ProfessorRepository;
import com.lt.crs.repository.RegisteredCourseRepository;
/**
 * 
 * @author user215
 *
 */
@Service
public class ProfessorService 
{
	
	@Autowired
	ProfessorRepository professorRepository ;
	

	@Autowired
	RegisteredCourseRepository registrationRepository;
	
	
	/**
	 * 
	 * @param profId
	 * @return
	 */
	public Professor getProfessorById(String profId) {
		Professor professorDetails = null;
		Optional<Professor> professorOptional =  professorRepository.findById(profId);

		if(professorOptional.isPresent()) {
			professorDetails  = professorOptional.get();
		}
		return  professorDetails;
	}
	
	/**
	 * 
	 * @param profId
	 * @return
	 */
	public List<EnrolledStudent> viewEnrolledStudents(String profId) {
		
		
        List<EnrolledStudent>  enrolledStudentList  = new ArrayList<EnrolledStudent>();
		
		List<Object> result = professorRepository.viewEnrolledStudent(profId);
		for(Object eachResult : result){
			EnrolledStudent  enrolledStudent  = new EnrolledStudent();
			Object[] obj =(Object[]) eachResult;
			if(obj[0]!=null) {
				String courseCode =obj[0].toString();
				System.out.println(courseCode);
				enrolledStudent.setCourseCode(courseCode);
			}
			if(obj[1]!=null) {
				String courseName =obj[1].toString();
				System.out.println(courseName);
				enrolledStudent.setCourseName(courseName);
			}

			if(obj[2]!=null) {
				String studentId =obj[2].toString();
				System.out.println(studentId);
				enrolledStudent.setStudentId(studentId);
			}
			enrolledStudentList.add(enrolledStudent);
			
		}
		return enrolledStudentList;
		
	}

	/**
	 * 
	 * @param studentID
	 * @param courseID
	 * @param grade
	 * @return
	 */
	public boolean addGrade(String studentID, String courseID, String grade) {
		List<RegisteredCourse> registeredCourseList=registrationRepository.findByCourseCodeAndStudentId(courseID,studentID);
		for( RegisteredCourse registeredCourse  :registeredCourseList) {
			registeredCourse.setGrade(grade);
			registrationRepository.save(registeredCourse);
		}
		if(registeredCourseList !=null && registeredCourseList.size()!=0) {
			return true;
		}
		
		return false;
	}
}
