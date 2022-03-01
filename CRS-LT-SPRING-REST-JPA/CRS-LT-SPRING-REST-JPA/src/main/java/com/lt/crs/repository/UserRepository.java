package com.lt.crs.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lt.crs.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	Optional<User> findByName(String name);




}
