package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lt.crs.model.Course;
import com.lt.crs.model.Grade;

public interface CourseRepository extends CrudRepository<Course, String> {
   
	
	@Query(value = "select * from course c where c.courseCode not in  (select courseCode  from registeredcourse where studentId =:studentID) and availableSeats > 0",nativeQuery=true)
	public abstract List<Course> viewCourse(@Param( value ="studentID") String studentID);

	@Query("select c.courseCode,c.courseName,r.grade from Course c inner join RegisteredCourse  r on c.courseCode = r.courseCode where r.studentId =:studentID")
	public abstract List<Object> viewGradeCard(@Param( value ="studentID") String studentID);
	
}
