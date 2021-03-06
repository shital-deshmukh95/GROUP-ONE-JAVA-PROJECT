package com.lt.crs.controller;

import java.util.ArrayList;
import java.util.List;

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

//import com.crs.lt.bean.EnrolledStudent;
//import com.crs.lt.business.ProfessorService;
//import com.crs.lt.business.UserService;
//import com.crs.lt.controller.Logger;
//import com.crs.lt.controller.ProfessorRestApi;


import com.crs.lt.exceptions.UserNotFoundException;
import com.lt.crs.model.EnrolledStudent;
import com.lt.crs.model.Professor;
import com.lt.crs.service.ProfessorService;

@RestController
@CrossOrigin
public class ProfessorCRSRestApi 
{
	@Autowired
	ProfessorService professorService;
	

	
	/**
	 * 
	 * @param studentID
	 * @param courseID
	 * @param grade
	 * @return
	 */
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

	 /**
	  * 
	  * @param profId
	  * @return
	  */
	@RequestMapping(method = RequestMethod.GET, value = "/getProfessorById")
	@ResponseBody
	public ResponseEntity<?> getProfessorById(@RequestParam("profId") String profId) {
		Professor result;
		try {
			result = professorService.getProfessorById(profId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	/**
	 * 
	 * @param profId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/viewEnrolledStudents/{profId}")
	@ResponseBody
	public ResponseEntity<?> viewEnrolledStudents(@PathVariable("profId") String profId) {

		List<EnrolledStudent> result = new ArrayList<EnrolledStudent>();
		try {
			result = professorService.viewEnrolledStudents(profId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
