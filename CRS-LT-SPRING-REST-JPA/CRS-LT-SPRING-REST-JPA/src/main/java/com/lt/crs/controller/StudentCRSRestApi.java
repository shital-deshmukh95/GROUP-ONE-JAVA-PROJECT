package com.lt.crs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.repository.StudentRepository;

@RestController
@CrossOrigin
public class StudentCRSRestApi {
	@Autowired
    StudentRepository  studentRepository ;
	@RequestMapping( method = RequestMethod.GET, value = "/student")
	@ResponseBody
	public ResponseEntity<?> viewCourses() throws SQLException {
		return ResponseEntity.status(HttpStatus.OK).body(studentRepository.findById("1").get());
	}




}
