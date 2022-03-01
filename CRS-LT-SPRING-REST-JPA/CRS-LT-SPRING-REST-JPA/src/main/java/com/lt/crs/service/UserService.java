package com.lt.crs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.model.User;
import com.lt.crs.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository ;

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


}
