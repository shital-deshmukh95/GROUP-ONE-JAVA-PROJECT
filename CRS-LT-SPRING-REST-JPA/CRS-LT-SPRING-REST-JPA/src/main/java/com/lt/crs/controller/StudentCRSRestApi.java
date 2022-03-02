package com.lt.crs.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.SeatNotAvailableException;
import com.crs.lt.exceptions.StudentException;
import com.lt.crs.model.Course;
import com.lt.crs.repository.StudentRepository;
import com.lt.crs.service.RegisteredCourseService;
import com.lt.crs.service.StudentService;

@RestController
@CrossOrigin
public class StudentCRSRestApi {

	@Autowired
	private StudentService studentService;

	@Autowired
	private RegisteredCourseService registeredCourseService;


	@RequestMapping(method = RequestMethod.GET, value = "/courses")
	@ResponseBody
	public ResponseEntity<?> viewCourses(@RequestParam(value ="studentId") String studentId)  {
		List<Course> courseList = new ArrayList<Course>();
		try {
			courseList = studentService.viewCourses(studentId);

		} catch (Exception e) {
			e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in viewing courses");
		}

		return ResponseEntity.status(HttpStatus.OK).body(courseList);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/student/addCourse")
	@ResponseBody
	public ResponseEntity<?> addCourse(@RequestParam(value = "courseCode") String courseCode,
			@RequestParam(value = "studentId", required = true) String studentId)
	{

		try {
			
			boolean b = registeredCourseService.addCourse(courseCode,studentId);
		} catch (StudentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		catch (CourseNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Course successfully added : " + studentId);

	}

	
	@RequestMapping(method = RequestMethod.DELETE, value = "/dropCourse")
	@ResponseBody
	public ResponseEntity<?> dropCourse(@RequestParam(value = "courseCode") String courseCode,
			@RequestParam(value = "studentId") String studentId) throws SQLException {
		
		  try {
			boolean registration = studentService.dropCourse(courseCode,studentId);
		} catch (StudentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		  return ResponseEntity.status(HttpStatus.OK).body("Course deleted  : " + studentId);

	}


}
