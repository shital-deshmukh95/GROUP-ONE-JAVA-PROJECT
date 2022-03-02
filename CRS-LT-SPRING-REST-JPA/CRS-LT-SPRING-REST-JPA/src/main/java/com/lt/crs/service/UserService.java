package com.lt.crs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crs.lt.constant.RoleConstant;
import com.crs.lt.exceptions.StudentException;
import com.crs.lt.exceptions.UserNotFoundException;
import com.lt.crs.model.Student;
import com.lt.crs.model.User;
import com.lt.crs.repository.StudentRepository;
import com.lt.crs.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository ;

	@Autowired
	StudentService studentService;
	/**
	 * 
	 * @return
	 */
	public List<User> getAllUser() {
		return   (List<User>)userRepository.findAll();
	}


	/**
	 * 
	 * @param userId
	 * @return name
	 */
	public String getName(String userId) {
		String name = null;
		Optional<User> user =  userRepository.findById(userId);

		if(user.isPresent()) {
			name  = user.get().getName();
		}
		return  name;
	}

	/**
	 * 
	 * @param userId
	 * @return role
	 */
	public String getRole(String userId) {
		String role = null;
		Optional<User> user =  userRepository.findById(userId);

		if(user.isPresent()) {
			role  = user.get().getRole();
		}
		return  role;
	}

	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	public boolean verifyCredentials(String userId, String password) throws UserNotFoundException {
		boolean  result = false;
		Optional<User> user =  userRepository.findById(userId);

		if(!user.isPresent()) {
			UserNotFoundException e = new UserNotFoundException();
			e.setMessage("User Not found with the userID : " + userId);
			throw e;
		}

		if(user.isPresent()) {
			if(password.equals(user.get().getPassword())) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 * @throws StudentException
	 */

	public String login(String userId ,String password) throws UserNotFoundException, StudentException {
		System.out.println(userId);
		System.out.println(password);
		String result = null;
		boolean isValidated = this.verifyCredentials(userId, password);
		if(isValidated) {
			String role = this.getRole(userId);
			if(role != null) {
				if("STUDENT".equalsIgnoreCase(role)) {
					RoleConstant roleCo = RoleConstant.STUDENT;
					boolean isApprove = studentService.isApproved(userId);////// need to change 
					if (!isApprove) {
						result = "Your admission request is still pending..., login later";
					} else
						result = "Role Type: " + roleCo + " Logged In Successfully";
				}//student 

				if("PROFESSOR".equalsIgnoreCase(role)) {
					RoleConstant roleCo = RoleConstant.PROFESSOR;
					result = "Role Type: " + roleCo + " Logged In Successfully";

				}//PROFESSOR

				if("ADMIN".equalsIgnoreCase(role)) {
					RoleConstant roleCo = RoleConstant.ADMIN;
					result = "Role Type: " + roleCo + " Logged In Successfully";
				}
			}
		}

		if(!isValidated) {
			UserNotFoundException e = new UserNotFoundException();
			e.setMessage("Check the Credintial with the userID : " + userId);
			throw e;
		}



		return result;


	}

	/**
	 * 
	 * @param userId
	 * @param password
	 * @param newPassword
	 * @return
	 * @throws UserNotFoundException
	 */
	public boolean updatePassword(String userId, String password,String newPassword) throws UserNotFoundException {
		boolean  result = false;
		boolean isVerify = this.verifyCredentials(userId, password);

		if(!isVerify) {
			UserNotFoundException e = new UserNotFoundException();
			e.setMessage("Please check the Credintial : " + userId);
			throw e;
		}

		Optional<User> userOptional =  userRepository.findById(userId);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			user.setPassword(newPassword);
			userRepository.save(user);
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * @param student
	 * @return
	 * @throws StudentException
	 */
	public boolean register(Student student) throws StudentException {

		Optional<User> op = userRepository.findById(student.getStudentId());

		if(op.isPresent()) {
			StudentException e = new StudentException();
			e.setMessage("Duplicate userId : " + student.getStudentId());
			throw e;
		}
		boolean  isRegister = false;
		User user =new User();
		user.setAddress(student.getAddress());
		user.setGender(student.getGender());
		user.setName(student.getName());
		user.setPassword(student.getPassword());
		if(student.getRole()==null) {
			user.setRole("STUDENT");
		}else {
			user.setRole(student.getRole());
		}
		user.setUserId(student.getStudentId());
		userRepository.save(user);

		student.setIsApproved("0");
		student.setIsRegistered("0");
		student.setIsPaid("0");
		student.setIsReportGenerated("0");

		String stusentId = studentService.register(student);
		if(stusentId!=null) {
			isRegister = true ;
		}
		return isRegister;
	}





}
