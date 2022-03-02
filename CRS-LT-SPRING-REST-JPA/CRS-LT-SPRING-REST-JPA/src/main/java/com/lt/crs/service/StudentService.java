package com.lt.crs.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.StudentException;
import com.lt.crs.model.Course;
import com.lt.crs.model.Student;
import com.lt.crs.repository.CourseRepository;
import com.lt.crs.repository.RegisteredCourseRepository;
import com.lt.crs.repository.StudentRepository;


@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	RegisteredCourseRepository registeredCourseRepository;
	
	
/**
 * 
 * @param studentId
 * @return
 * @throws StudentException
 */
	public boolean isApproved(String studentId) throws StudentException {
		boolean isApproved = false;
		Optional<Student> student = studentRepository.findById(studentId);

		if(student.isPresent()) {
			if("1".equalsIgnoreCase(student.get().getIsApproved())) {
				isApproved = true;
			}

		}
		
		if(!student.isPresent()) {
			StudentException e = new StudentException();
			e.setMessage("Student Not Found with the studentID" + studentId);
			throw  e;
			
		}
		

		return isApproved;
	}

	/**
	 * 
	 * @param student
	 * @return
	 */
	public String register(Student student) {
		Student newstudent = studentRepository.save(student);
		return newstudent.getStudentId();
		
	}

	public List<Course> viewCourses(String studentId) {
		
		System.out.println("viewCourses");
		List<Course>  list = new ArrayList<Course>();
		list = courseRepository.viewCourse(studentId);
		return list;
	}

	public boolean dropCourse(String courseCode, String studentId) throws StudentException {
		Optional<Student> student = studentRepository.findById(studentId);

		if(student.isPresent()) {
			if("0".equalsIgnoreCase(student.get().getIsApproved())) {
				StudentException e = new StudentException();
				e.setMessage("Student course registration is pending");
				throw  e;
			}
		}
		
		List<Course> registeredCourseList = registeredCourseRepository.viewRegisteredCourses(studentId);
		System.out.println("Course code" + courseCode);
		System.out.println("studentid" + studentId);
		 int row  = registeredCourseRepository.deleteByCourseCodeAndStudentId(courseCode,studentId);// need to check
		 if(row !=0) {
			  return true;
		 }

		return false;
	}
}
