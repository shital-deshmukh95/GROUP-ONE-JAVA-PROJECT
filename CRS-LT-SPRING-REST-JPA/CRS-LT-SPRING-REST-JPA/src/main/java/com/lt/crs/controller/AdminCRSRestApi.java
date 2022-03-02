package com.lt.crs.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.exceptions.CourseExistsAlreadyException;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.StudentException;
import com.lt.crs.model.Catalog;
import com.lt.crs.service.AdminService;

@RestController
@CrossOrigin
public class AdminCRSRestApi {

	@Autowired
	AdminService adminService;

	//	@Autowired
	//	UserService userService;
	//	
	//	@Autowired
	//	RegistrationService registrationService;
	//	private static Logger logger =Logger.getLogger(AdminCRSRestApi.class);
	//	

	@RequestMapping(method = RequestMethod.GET, value = "/admin/viewCourses")
	@ResponseBody
	public ResponseEntity<?> viewCourses() {
		List<Catalog> result = new ArrayList<Catalog>();
		try {
			//result = adminService.viewCourses();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	//	 
	//	 @RequestMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, method = RequestMethod.POST,value = "/admin/addCourse")
	//		@ResponseBody
	//	    public ResponseEntity<?> addCourse(@RequestBody Catalog course) throws SQLException, CourseExistsAlreadyException {
	//		
	//	       
	//		 List<Catalog> courseList = adminService.viewCourses();
	//	        try {
	//	        	adminService.addCourse(course, courseList);
	//	        	List<Catalog> courseListView = adminService.viewCourses();
	//	        	return ResponseEntity.status(HttpStatus.OK).body(courseListView);
	//	        }
	//	        catch(SQLException | CourseExistsAlreadyException e){
	//	        	logger.error(e.getMessage());
	//	        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	//	        }
	//	       
	//	    }
	//	 
	//	 /**
	//		 * /admin/deleteCourse
	//		 * REST-services for dropping a course from catalog
	//		 * @param courseCode
	//		 * @return
	//	 * @throws SQLException 
	//		 */
	//		
	//		 @RequestMapping(method = RequestMethod.DELETE,value = "/deleteCourse")
	//		@ResponseBody
	//		public ResponseEntity<?> deleteCourse(
	//				@RequestParam( value = "courseCode") String courseCode) throws SQLException {
	//			List<Catalog> courseList = adminService.viewCourses();
	//			
	//			try {
	//				
	//				adminService.removeCourse(courseCode, courseList);
	//				return ResponseEntity.status(HttpStatus.OK).body("Course with courseCode: " + courseCode + " deleted from catalog");
	//			
	//			} catch (CourseNotFoundException| CourseNotDeletedException | SQLException e) {
	//				
	//				return ResponseEntity.status(409).body(e.getMessage());
	//			
	//			}	
	//		}

	@RequestMapping(method = RequestMethod.POST,value = "/admin/approve")
	@ResponseBody
	public ResponseEntity<?> approveAdmissionRequest(@RequestParam(value = "studentId") String studentId) {
		try {
			boolean isApproved = adminService.approveStudentRequest(studentId);
			System.out.println(isApproved);
			if (isApproved) {
				return ResponseEntity.status(HttpStatus.OK).body("Admission Request for student with student ID: " + studentId + " approved successfully.");

			}
		} catch (StudentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(400).body("Admission Request for student with student ID: " + studentId + " cannot be approved.") ;

	}
	//
	//		 private List<Student> viewPendingAdmissions() throws SQLException {
	//				
	//				List<Student> pendingStudentsList= adminService.viewPendingAdmissions();
	//				if(pendingStudentsList.size() == 0) {
	//					System.out.println("No students pending approvals");
	//					return pendingStudentsList;
	//				}
	//				System.out.println(String.format("%20s | %20s | %20s", "StudentId", "Name", "GenderConstant"));
	//				for(Student student : pendingStudentsList) {
	//					System.out.println(String.format("%20s | %20s | %20s", student.getStudentId(), student.getName(), student.getGender().toString()));
	//				}
	//				return pendingStudentsList;
	//			}
	//		 
	//		 @RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET,value = "/admin/generateReport")
	//			@ResponseBody
	//		 public ResponseEntity<?> generateReportCard(@RequestParam(value = "studentId") String studentId) throws SQLException{
	//			 List<Grade> grade_card=null;
	//				boolean isReportGenerated = true;
	//				adminService.setGeneratedReportCardTrue(studentId);
	//				if(isReportGenerated) {
	//					grade_card = registrationService.viewGradeCard(studentId);
	//					if(grade_card.isEmpty())
	//					{
	//						logger.info("You haven't registered for any course");
	//						return ResponseEntity.status(HttpStatus.OK).body("You haven't registered for any course");
	//					}
	//					else {
	//						logger.info("Report card not yet generated");
	//						return ResponseEntity.status(HttpStatus.OK).body("Report card not yet generated");
	//					}
	//				}
	//				
	//				
	//			 return ResponseEntity.status(HttpStatus.OK).body("Report generated");
	//			 
	//		 }
}
