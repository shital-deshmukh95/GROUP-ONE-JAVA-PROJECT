package com.crs.lt.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crs.lt.bean.EnrolledStudent;
import com.crs.lt.business.ProfessorService;
import com.crs.lt.business.UserService;
import com.crs.lt.constant.RoleConstant;

@RestController
@CrossOrigin
public class ProfessorRestApi {

	@Autowired
	ProfessorService professorService;
	
	@Autowired
	UserService userService;

	private static Logger logger = Logger.getLogger(ProfessorRestApi.class);
//addGread
	 @RequestMapping(method = RequestMethod.POST,value = "/addGrade")
	 @ResponseBody
	public ResponseEntity<?> addGrade(@RequestParam(value ="studentID") String studentID,
			@RequestParam(value ="courseID") String courseID, @RequestParam(value ="grade") String grade) {
		boolean result;
		try {
			result = professorService.addGrade(studentID, courseID, grade);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

//getProfessorById
	@RequestMapping(method = RequestMethod.GET, value = "/getProfessorById")
	@ResponseBody
	public ResponseEntity<?> getProfessorById(@RequestParam("profId") String profId) {
		String result;
		try {
			result = professorService.getProfessorById(profId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/viewEnrolledStudents/{profId}")
	@ResponseBody
	public ResponseEntity<?> viewEnrolledStudents(@PathVariable("profId") String profId) {
//		 if (userService.user == null || !userService.user.getRole().equals(RoleConstant.PROFESSOR)) {
//	            logger.info("Error: User not authenticated.");
//	            return null;
//	        }
		List<EnrolledStudent> result = new ArrayList<EnrolledStudent>();
		try {
			result = professorService.viewEnrolledStudents(profId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
