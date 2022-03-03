package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lt.crs.model.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, String> 
{

	@Query("select c.courseCode,c.courseName,r.studentId from Course c inner join RegisteredCourse r on c.courseCode = r.courseCode where c.instructorId =:instructorId order by c.courseCode")
	public abstract List<Object> viewEnrolledStudent(@Param( value ="instructorId") String instructorId);
}
