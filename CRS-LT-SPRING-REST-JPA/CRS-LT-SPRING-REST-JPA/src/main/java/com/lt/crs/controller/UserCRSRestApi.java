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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.model.User;
import com.lt.crs.service.UserService;

@RestController
@CrossOrigin
public class UserCRSRestApi {

	@Autowired
	UserService userService;


	@RequestMapping(method = RequestMethod.GET, value = "/user/getName/{userId}")
	@ResponseBody
	public ResponseEntity<?> getName(@PathVariable("userId") String userId) {
		String result;
		try {
			result = userService.getName(userId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/user/getRole/{userId}")
	@ResponseBody
	public ResponseEntity<?> getRole(@PathVariable("userId") String userId) {
		String result;
		try {
			result = userService.getRole(userId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
 
	@RequestMapping(method = RequestMethod.GET, value = "/user/getAllUser")
	@ResponseBody
	public ResponseEntity<?> getAllUser() {
		List<User> result = new ArrayList<User>();
		try {
			result = userService.getAllUser();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}



}