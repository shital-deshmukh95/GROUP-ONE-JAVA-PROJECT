package com.crs.lt.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crs.lt.bean.Catalog;
import com.crs.lt.bean.Course;
import com.crs.lt.bean.Student;
import com.crs.lt.business.AdminService;
import com.crs.lt.business.UserService;
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;

@RestController
@CrossOrigin
public class AdminRestApi {

	@Autowired
	AdminService adminService;
	
	@Autowired
	UserService userService;
	private static Logger logger =Logger.getLogger(AdminRestApi.class);
	
	 @RequestMapping( method = RequestMethod.GET,value = "/admin/courses")
		@ResponseBody
	private ResponseEntity<?>  viewCourses() throws SQLException {
		 System.out.println("In view courses");
		List<Catalog> courseList = new ArrayList<>();
		try {
			courseList = adminService.viewCourses();
			if(courseList.size() == 0) {
				logger.error("Nothing present in the catalogue!");
				return ResponseEntity.status(HttpStatus.OK).body(courseList);
			}
			logger.info("Courses present in the course catalogue!!");
			return ResponseEntity.status(HttpStatus.OK).body(courseList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
		
	}
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/test")
		@ResponseBody
		public ResponseEntity<?> getRole() {
			String result;
			try {
				result = "Sucess";
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
	 
	
	 
	 @RequestMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, method = RequestMethod.POST,value = "/admin/addCourse")
		@ResponseBody
	    public ResponseEntity<?> addCourse(@RequestBody Catalog course) throws SQLException, CourseExistsAlreadyException {
		
	       
		 List<Catalog> courseList = adminService.viewCourses();
	        try {
	        	adminService.addCourse(course, courseList);
	        	List<Catalog> courseListView = adminService.viewCourses();
	        	return ResponseEntity.status(HttpStatus.OK).body(courseListView);
	        	//return ResponseEntity.status(HttpStatus.OK).body("Course Added sucessfully");
	        }
	        catch(SQLException | CourseExistsAlreadyException e){
	        	logger.error(e.getMessage());
	        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	        }
	       
	    }
	 
	 /**
		 * /admin/deleteCourse
		 * REST-services for dropping a course from catalog
		 * @param courseCode
		 * @return
	 * @throws SQLException 
		 */
		
		 @RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE,value = "/deleteCourse")
		@ResponseBody
		public ResponseEntity<?> deleteCourse(
				@RequestParam( value = "courseCode") String courseCode) throws SQLException {
			List<Catalog> courseList = adminService.viewCourses();
			
			try {
				
				adminService.removeCourse(courseCode, courseList);
				return ResponseEntity.status(HttpStatus.OK).body("Course with courseCode: " + courseCode + " deleted from catalog");
			
			} catch (CourseNotFoundException| CourseNotDeletedException | SQLException e) {
				
				return ResponseEntity.status(409).body(e.getMessage());
			
			}	
		}

		 @RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST,value = "/approve")
			@ResponseBody
			public ResponseEntity<?> approveAdmissionRequest(@RequestParam(value = "studentId") String studentId) throws SQLException {
			 List<Student> studentList = viewPendingAdmissions();
				
		      
		        try {
		            boolean isApproved = adminService.approveStudentRequest(studentId, studentList);
		            System.out.println(isApproved);
		            if (isApproved) {
		            	return ResponseEntity.status(HttpStatus.OK).body("Admission Request for student with student ID: " + studentId + " approved successfully.");
		                       
		            }
		            return ResponseEntity
		                    .status(400)
		                    .body("Admission Request for student with student ID: " + studentId + " cannot be approved.")
		                    ;
		        } catch (Exception e) {
		            throw e;
		        }

		    }

		 private List<Student> viewPendingAdmissions() throws SQLException {
				
				List<Student> pendingStudentsList= adminService.viewPendingAdmissions();
				if(pendingStudentsList.size() == 0) {
					System.out.println("No students pending approvals");
					return pendingStudentsList;
				}
				System.out.println(String.format("%20s | %20s | %20s", "StudentId", "Name", "GenderConstant"));
				for(Student student : pendingStudentsList) {
					System.out.println(String.format("%20s | %20s | %20s", student.getStudentId(), student.getName(), student.getGender().toString()));
				}
				return pendingStudentsList;
			}
}
