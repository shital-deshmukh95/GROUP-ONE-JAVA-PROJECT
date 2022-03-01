package com.crs.lt.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RestController;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.Grade;
import com.crs.lt.bean.GradeCard;
import com.crs.lt.bean.RegisterCourseModelClass;
import com.crs.lt.business.ProfessorInterface;
import com.crs.lt.business.ProfessorService;
import com.crs.lt.business.RegistrationInterface;
import com.crs.lt.business.RegistrationService;
import com.crs.lt.business.UserInterface;
import com.crs.lt.business.UserService;
import com.crs.lt.dao.AdminDoaOperation;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.SeatNotAvailableException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
//@Path("/student")
public class StudentRestApi {

	@Autowired
	RegistrationService registrationInterface;
	// RegistrationInterface registrationInterface = new RegistrationService();
	ProfessorInterface professorInterface = new ProfessorService();
	UserInterface userInterface = new UserService();

	// ObjectMapper mapper = new ObjectMapper();

	private static Logger logger = Logger.getLogger(StudentRestApi.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/courses")
	@ResponseBody
	public List<Course> viewCourses(@RequestParam(value ="studentId") String studentId) throws SQLException {
		// role = userInterface.getRole();
		List<Course> courseList = new ArrayList<Course>();
		try {
			logger.info("In API");
			courseList = registrationInterface.viewCourses(studentId);
		} catch (SQLException e) {
			// TODO: handle exception
			logger.error("Error in viewing courses");
			e.getMessage();
		}

		return courseList;
	}

//	 @RequestMapping(
//			 produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/addCourse/{courseCode}/{studentId}")
//		@ResponseBody
//	    public ResponseEntity addcourse(@PathParam("courseCode") String courseCode,@PathParam("studentId")String studentId) throws SQLException, CourseNotFoundException, SeatNotAvailableException {
//		 
//		 List<Course> availableCourseList=registrationInterface.viewCourses(studentId);
//		 if(availableCourseList==null)
//				return new ResponseEntity("No COurses found for ID " + studentId, HttpStatus.NOT_FOUND);
//		 if(registrationInterface.getRegistrationStatus(studentId) == false)
//				return new ResponseEntity("Pending registration found for ID " + studentId, HttpStatus.OK);
//	        
//		 registrationInterface.addCourse(courseCode, studentId, availableCourseList);
//			return new ResponseEntity("Student registered " + studentId, HttpStatus.OK);
//
//	    }

	@RequestMapping(method = RequestMethod.POST, value = "/student/addCourse")
	@ResponseBody
	public ResponseEntity<?> addCourse(@RequestParam(value = "courseCode") String courseCode,
			@RequestParam(value = "studentId", required = true) String studentId)
			throws SQLException, CourseNotFoundException, SeatNotAvailableException {
		if (registrationInterface.getRegistrationStatus(studentId) == false)
			return ResponseEntity.status(200).body("Student course registration is pending");

		List<Course> availCourseList = registrationInterface.viewCourses(studentId);
		registrationInterface.addCourse(courseCode, studentId, availCourseList);

		return ResponseEntity.status(201).body("You have successfully added Course : " + courseCode);

	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE, value = "/dropCourse")
	@ResponseBody
	public ResponseEntity<?> dropCourse(@RequestParam(value = "courseCode") String courseCode,
			@RequestParam(value = "studentId") String studentId) throws SQLException {
		if (registrationInterface.getRegistrationStatus(studentId) == false)
			return ResponseEntity.status(200).body("Student course registration is pending");

		try {

			List<Course> registeredCourseList = registrationInterface.viewRegisteredCourses(studentId);
			System.out.println("Course code" + courseCode);
			System.out.println("studentid" + studentId);
			registrationInterface.dropCourse(courseCode, studentId, registeredCourseList);
			return ResponseEntity.status(200).body("You have successfully dropped Course : " + courseCode);
		} catch (CourseNotFoundException e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(501).body("You have not registered for course : " + e.getCourseCode());
		}

	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewRegisteredCourses")
	@ResponseBody
	public List<Course> viewRegisteredCourse(@RequestParam(value ="studentId") String studentId) throws SQLException {

		try {
			return registrationInterface.viewRegisteredCourses(studentId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@RequestMapping(value = "/make_payment", produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST)
	public ResponseEntity<?> makePayment(@RequestParam(value = "studentId") String studentId) throws SQLException {
		// TODO Auto-generated method stub
		double fee = 0;
		boolean isRegistered = false;
		boolean isPaid = false;
		isRegistered = registrationInterface.getRegistrationStatus(studentId);
		isPaid = registrationInterface.getPaymentStatus(studentId);
		if (!isRegistered) {
			System.out.println("You have not registered yet");
			return ResponseEntity.status(200).body("You havent registered for the course");
		}

		if (isRegistered && !isPaid) {
			try {
				fee = registrationInterface.calculateFee(studentId);
				System.out.println("Your total fee  = " + fee);
				// notificationInterface.sendNotification();
				System.out.println("Payment Successful by StudentId :" + studentId);
				registrationInterface.setPaymentStatus(studentId);
				return ResponseEntity.status(200).body("You have successfully completed the payment");

			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				return ResponseEntity.status(501).body("Your payment is not sucessfull");
			}

		}
		return ResponseEntity.status(501).body("Something Went wrong or you have already done the payment");

	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewGradeCard/{studentId}")
	public List<Grade> viewGradeCard(@PathVariable("studentId") String studentId) throws SQLException {
		List<Grade> grade_card = registrationInterface.viewGradeCard(studentId);
		return grade_card;

	}

	/**
	 * Method to handle API request for course registration
	 * 
	 * @param courseList
	 * @param studentId
	 * @throws ValidationException
	 * @return
	 * @throws SQLException
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/registerCourse")
	@ResponseBody
	public ResponseEntity<?> registerCourses(@RequestBody RegisterCourseModelClass courseList, @RequestParam("studentId") String studentId)
	{
		//String studentId = "1";
		try {
			List<Course> availableCourseList = registrationInterface.viewCourses(studentId);
			Set<String> hash_set = new HashSet<String>();

			for (String courseCode : courseList.getCourseList()) {
				registrationInterface.isCourseValid(courseCode, availableCourseList);

				if (!hash_set.add(courseCode))
					return ResponseEntity.status(500).body("Duplicate value  : " + courseCode);
			}
			List<Course> registeredCourseList = registrationInterface.viewRegisteredCourses(studentId);

			for (String courseCode : courseList.getCourseList())
				registrationInterface.addCourse(courseCode, studentId, availableCourseList);

			registrationInterface.setRegistrationStatus(studentId);
		} catch (SQLException | SeatNotAvailableException | CourseNotFoundException e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(500).body(e.getMessage());
		}

		return ResponseEntity.status(201).body("Registration Successful");

	}

}
