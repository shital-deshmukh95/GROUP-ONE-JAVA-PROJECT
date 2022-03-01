package com.lt.crs.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.lt.exceptions.StudentException;
import com.lt.crs.model.Student;
import com.lt.crs.repository.StudentRepository;


@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

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

	
	public String register(Student student) {
		Student newstudent = studentRepository.save(student);
		return newstudent.getStudentId();
		
	}
}
