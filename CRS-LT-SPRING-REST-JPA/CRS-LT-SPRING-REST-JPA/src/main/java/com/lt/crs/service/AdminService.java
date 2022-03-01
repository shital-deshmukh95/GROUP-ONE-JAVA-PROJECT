package com.lt.crs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.lt.exceptions.StudentException;
import com.lt.crs.model.Student;
import com.lt.crs.repository.StudentRepository;


@Service
public class AdminService {
	@Autowired
	StudentRepository studentRepository;
	//admin repo nedd to add

	public boolean approveStudentRequest(String studentId) throws StudentException {
		boolean isApprove =  false ;
		Optional<Student> student =  studentRepository.findById(studentId);
		if(student.isPresent()) {
			if("1".equalsIgnoreCase(student.get().getIsApproved())) {
				StudentException e =  new StudentException();
				e.setMessage("Student allready approved!");
				throw e;
				
			}
			student.get().setIsApproved("1");
			studentRepository.save(student.get());
			isApprove = true;
		}
		/// admin repo nedd calll here 

		return isApprove;
	}



}
