package com.lt.crs.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lt.crs.model.Student;

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

import com.crs.lt.exceptions.StudentException;
import com.crs.lt.exceptions.UserNotFoundException;
import com.lt.crs.model.User;
import com.lt.crs.service.UserService;

@RestController
@CrossOrigin
public class UserCRSRestApi {

	@Autowired
	UserService userService;

	/**
	 * 
	 * @param userId
	 * @return
	 */
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

	/**
	 * 
	 * @param userId
	 * @return
	 */
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

	/**
	 * @param :NA
	 * @return
	 */
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

	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/verifyCredentials/{userId}/{password}")
	@ResponseBody
	public ResponseEntity<?> verifyCredentials(@PathVariable(value = "userId") String userId,
			@PathVariable(value = "password") String password) {
		boolean result;
		try {
			result = userService.verifyCredentials(userId, password);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> login(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "password") String password)  {
		String result;
		try {
			result = userService.login(userId, password);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

/**
 * 
 * @param userId
 * @param newPassword
 * @return
 */
	@RequestMapping(method = RequestMethod.PUT, value = "/user/updatePassword")
	@ResponseBody
	public ResponseEntity<?> updatePassword(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "password") String  password ,
			@RequestParam(value = "newPassword") String newPassword) {
		boolean result;
		try {
			result = userService.updatePassword(userId,password, newPassword);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	@ResponseBody
	public ResponseEntity<?> register(@RequestBody Student student) throws Exception {
		boolean result = false ;
		try {
			result = userService.register(student);
		}
		catch (StudentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(" you are successfully registered, please wait for Admin's Approval" + result);
	}


}