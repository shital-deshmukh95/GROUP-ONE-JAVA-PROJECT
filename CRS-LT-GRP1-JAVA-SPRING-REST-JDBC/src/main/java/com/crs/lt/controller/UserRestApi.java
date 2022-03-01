package com.crs.lt.controller;

import java.sql.SQLException;

import javax.websocket.server.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

import com.crs.lt.bean.Student;
import com.crs.lt.bean.User;
import com.crs.lt.business.AdminService;
import com.crs.lt.business.ProfessorService;
import com.crs.lt.business.StudentService;
import com.crs.lt.business.UserService;
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.exceptions.UserNotFoundException;

@RestController
@CrossOrigin
public class UserRestApi {

	@Autowired
	UserService userService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	AdminService adminService;
	
	 
	   @RequestMapping(value = "/login",  method = RequestMethod.POST)
	   @ResponseBody
	    public ResponseEntity<?> login(@RequestParam(value = "userId") String userId, @RequestParam(value = "password") String password)
	    		throws UserNotFoundException, SQLException {
		  System.out.println(userId);
		  System.out.println(password);
	        boolean isValidated = userService.verifyCredentials(userId, password);
	        System.out.println(isValidated);
	        if (isValidated) {
	            if (("STUDENT").equals(userService.getRole(userId))) {
	            	RoleConstant role = RoleConstant.STUDENT;
	            	if (!studentService.isApproved(userId)) {
	            		return ResponseEntity.status(HttpStatus.OK).body("Your admission request is still pending..., login later");
	            	}
	            	else 
	            		return ResponseEntity.status(HttpStatus.OK).body("Role Type: " +role +" Logged In Successfully");
	            		

				}
				if (("PROFESSOR").equals(userService.getRole(userId))) {
					professorService.getProfessorById(userId);
					RoleConstant role = RoleConstant.PROFESSOR;
					return ResponseEntity.status(HttpStatus.OK).body("Role Type: " + role +" Logged In Successfully");
				}
				 if (("ADMIN").equals(userService.getRole(userId))) {
					 RoleConstant role = RoleConstant.ADMIN;
					 return ResponseEntity.status(HttpStatus.OK).body("Role Type: " +role +" Logged In Successfully");
				 }

	        }
	        return ResponseEntity.status(HttpStatus.OK).body("Invalid userId or Password");
	    }


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

	@RequestMapping(method = RequestMethod.GET, value = "/user/verifyCredentials/{userId}/{password}")
	@ResponseBody
	public ResponseEntity<?> verifyCredentials(@PathVariable("userId") String userId,
			@PathVariable("password") String password) {
		boolean result;
		try {
			result = userService.verifyCredentials(userId, password);
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

	@RequestMapping(method = RequestMethod.PUT, value = "/user/updatePassword")
	@ResponseBody
	public ResponseEntity<?> updatePassword(@RequestParam(value = "userId") String userId,
			@RequestParam(value ="newPassword") String newPassword) {
		boolean result;
		try {
			result = userService.updatePassword(userId, newPassword);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	   
	   @RequestMapping(method = RequestMethod.POST, value = "/register", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
		@ResponseBody
	    public ResponseEntity<?> register(@RequestBody Student student) throws Exception {
	        try {
	       
	            String st = 
	            studentService.register(student.getName(), student.getUserId(), student.getPassword(), student.getGender(), student.getBatch(), student.getBranchName(), student.getAddress());
	            if (st == null) {
	            	return ResponseEntity.status(HttpStatus.OK).body("Something Went wrong try again later");
	            }
	            return ResponseEntity.status(201).body( " you are successfully registered, please wait for Admin's Approval");
	        } catch (Exception e) {
	            throw e;
	        }

	    }
}