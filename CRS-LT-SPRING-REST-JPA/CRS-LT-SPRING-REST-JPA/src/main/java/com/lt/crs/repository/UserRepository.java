package com.lt.crs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lt.crs.model.User;

public interface UserRepository extends CrudRepository<User, String> {




}
