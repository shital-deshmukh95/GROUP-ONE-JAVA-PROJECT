package com.lt.crs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.StudentException;
import com.lt.crs.model.Course;
import com.lt.crs.model.Grade;
import com.lt.crs.model.RegisteredCourse;
import com.lt.crs.model.Student;
import com.lt.crs.repository.CourseRepository;
import com.lt.crs.repository.RegisteredCourseRepository;
import com.lt.crs.repository.StudentRepository;

/**
 * @author user213
 *
 */
@Service
public class RegisteredCourseService {

	@Autowired
	RegisteredCourseRepository registrationRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	public boolean addCourse(String courseCode,String studentId , RegisteredCourse registeredCourse) throws StudentException, CourseNotFoundException {
		System.out.println(courseCode);
		
		boolean result =false;
		Optional<Student> student =	studentRepository.findById(studentId);
		if(student.isPresent()) {
			if("0".equalsIgnoreCase(student.get().getIsRegistered())) {
				StudentException s = new StudentException();
				s.setMessage("Student course registration is pending");
				throw s;
			}

			List<Course> courseList =courseRepository.viewCourse(studentId);


			Optional<Course> course =	courseRepository.findById(courseCode);
			if(course.isPresent()) {
				if(course.get().getAvailableSeats()<=0) {
					StudentException s = new StudentException();
					s.setMessage("Seats are not available in : " + courseCode);
					throw s;
				}
			}
			else if(!isCourseValid(courseCode, courseList))
			{
				CourseNotFoundException e = new CourseNotFoundException();
				e.setMessage("Course with courseCode: " + courseCode + " not found.");
				throw e;
			}

			String grade="-";
			
			registeredCourse.setCourseCode(courseCode);
			registeredCourse.setStudentId(studentId);
			registeredCourse.setGrade(grade);
			System.out.println(registeredCourse.toString());
			registrationRepository.save(registeredCourse);
			Optional<Course> courseOptional = courseRepository.findById(courseCode);
			if(courseOptional.isPresent()) {
				Course course1 = courseOptional.get();
				if(course1.getAvailableSeats()!=0) {
					course1.setAvailableSeats(course1.getAvailableSeats() - 1);
				}
				courseRepository.save(course1);
			}

			System.out.println("Course added");
			result = true ;
		}

		return result;// need to change
	}

	public void setRegistrationStatus(String studentId) {
		Optional<Student> studentList=studentRepository.findById(studentId);
		if(studentList.isPresent()) {
			Student student=studentList.get();
			student.setIsRegistered("1");
			studentRepository.save(student);
		}

	}

	public boolean getRegistrationStatus(String studentId) {
		Optional<Student> studentList=studentRepository.findById(studentId);
		if(studentList.isPresent()) {
			studentList.get().getIsRegistered();
			return true;
		}else {
			return false;
		}

	}


	public boolean isCourseValid (String courseCode,List<Course>availableCourseList){

		return availableCourseList.stream().anyMatch(c -> c.getCourseCode().equalsIgnoreCase(courseCode));


	}

	public List<Grade> viewGradeCard(String studentId) {
		
		List<Grade>  gradelist  = new ArrayList<Grade>();
		
		List<Object> result = courseRepository.viewGradeCard(studentId);
		for(Object eachResult : result){
			Grade  grade  = new Grade();
			Object[] obj =(Object[]) eachResult;
			if(obj[0]!=null) {
				String courseCode =obj[0].toString();
				System.out.println(courseCode);
				grade.setCourseCode(courseCode);
			}

			if(obj[1]!=null) {
				String courseName =obj[1].toString();
				System.out.println(courseName);
				grade.setCourseName(courseName);
			}

			if(obj[2]!=null) {
				String grade1 =obj[2].toString();
				System.out.println(grade1);
				grade.setGrade(grade1);
			}
			gradelist.add(grade);
			
		}

		return gradelist;
	}

	


}
