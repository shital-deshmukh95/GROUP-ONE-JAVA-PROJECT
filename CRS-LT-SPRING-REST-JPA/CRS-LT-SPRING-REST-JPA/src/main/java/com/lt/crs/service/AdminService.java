package com.lt.crs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.lt.exceptions.StudentException;
import com.lt.crs.model.Catalog;
import com.lt.crs.model.RegisteredCourse;
import com.lt.crs.model.Student;
import com.lt.crs.repository.AdminRepository;
import com.lt.crs.repository.StudentRepository;

/**
 * 
 * @author user215
 *
 */
@Service
public class AdminService {
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	AdminRepository adminRepository ;

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws StudentException
	 */
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
	
	public List<Catalog> viewCourses() {
		return null;
		// TODO Auto-generated method stub
		//return adminRepository.viewCourses(); 
	}


	public void setGeneratedReportCardTrue(String Studentid) {
		// TODO Auto-generated method stub
		//adminRepository.setGeneratedReportCardTrue(Studentid);
		
	}

	public List<RegisteredCourse> generateGradeCard(String Studentid) {
		return null;
		// TODO Auto-generated method stub
		//return adminRepository.generateGradeCard(Studentid);
	}

	
	

}
