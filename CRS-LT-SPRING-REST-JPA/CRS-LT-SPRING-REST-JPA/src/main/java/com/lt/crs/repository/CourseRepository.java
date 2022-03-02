package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lt.crs.model.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
   
	
	@Query(value = "select * from course where courseCode not in  (select courseCode  from registeredcourse where studentId =:studentID) and availableSeats > 0",nativeQuery=true)
	public abstract List<Course> viewCourse(@Param( value ="studentID") String studentID);
	
}
