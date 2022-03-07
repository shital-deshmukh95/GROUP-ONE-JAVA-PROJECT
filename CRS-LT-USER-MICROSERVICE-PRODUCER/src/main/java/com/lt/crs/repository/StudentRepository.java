package com.lt.crs.repository;

import org.springframework.data.repository.CrudRepository;

import com.lt.crs.model.Student;
/**
 * 
 * @author user215
 *
 */
public interface StudentRepository extends CrudRepository<Student, String> {

	
	
}
