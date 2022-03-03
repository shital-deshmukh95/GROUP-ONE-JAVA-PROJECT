package com.lt.crs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.StudentException;
import com.lt.crs.model.Catalog;
import com.lt.crs.model.EnrolledStudent;
import com.lt.crs.model.RegisteredCourse;
import com.lt.crs.model.Student;
import com.lt.crs.repository.AdminRepository;
import com.lt.crs.repository.CatalogRepository;
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
	
	@Autowired
	CatalogRepository catalogRepository;

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
	
//	public List<Catalog> viewCourses() {
//
//		return catalogRepository.findAll();
//		// TODO Auto-generated method stub
//		//return adminRepository.viewCourses(); 
//	}
	
	public List<Catalog> viewCourses(String profId) {
		
//		
//        List<EnrolledStudent>  enrolledStudentList  = new ArrayList<EnrolledStudent>();
//		
//		List<Object> result = professorRepository.viewEnrolledStudent(profId);
//		for(Object eachResult : result){
//			EnrolledStudent  enrolledStudent  = new EnrolledStudent();
//			Object[] obj =(Object[]) eachResult;
//			if(obj[0]!=null) {
//				String courseCode =obj[0].toString();
//				System.out.println(courseCode);
//				enrolledStudent.setCourseCode(courseCode);
//			}
//			if(obj[1]!=null) {
//				String courseName =obj[1].toString();
//				System.out.println(courseName);
//				enrolledStudent.setCourseName(courseName);
//			}
//
//			if(obj[2]!=null) {
//				String studentId =obj[2].toString();
//				System.out.println(studentId);
//				enrolledStudent.setStudentId(studentId);
//			}
//			enrolledStudentList.add(enrolledStudent);
//			
//		}
		return null;
		
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

	public void removeCourse(String courseCode) {
		catalogRepository.deleteById(courseCode);
		// TODO Auto-generated method stub
		
	}

	public void addCourse(Catalog course, List<Catalog> courseList) throws CourseExistsAlreadyException {
		try {
			if(!isValidAddCourse(course, courseList)) {
				System.out.println("courseCode: " + course.getCourseCode() + " already present");
				throw new CourseExistsAlreadyException(course.getCourseCode());
			}
			Catalog catalogCourse=new Catalog();
			catalogCourse.setCourseCode(course.getCourseCode());
			catalogCourse.setCourseName(course.getCourseName());
			catalogCourse.setDescription(course.getDescription());
			catalogRepository.save(catalogCourse);
		}
		catch(CourseExistsAlreadyException e) {
			throw e;
		}
		
	}

	private boolean isValidAddCourse(Catalog course, List<Catalog> courseList) {
		for(Catalog addCourse : courseList) {
			if(addCourse.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
				return false; 
			}
		}
		return true;
	}

	
	

}
