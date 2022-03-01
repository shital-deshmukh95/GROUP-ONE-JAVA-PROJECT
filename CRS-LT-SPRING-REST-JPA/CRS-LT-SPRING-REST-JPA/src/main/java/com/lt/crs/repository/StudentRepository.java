package com.lt.crs.repository;

import org.springframework.data.repository.CrudRepository;

import com.lt.crs.model.Student;

public interface StudentRepository extends CrudRepository<Student, String> {

	
	
}
