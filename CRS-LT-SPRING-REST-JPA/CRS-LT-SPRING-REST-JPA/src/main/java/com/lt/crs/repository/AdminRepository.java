package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lt.crs.model.Admin;
import com.lt.crs.model.Catalog;
import com.lt.crs.model.RegisteredCourse;


public interface AdminRepository extends CrudRepository<Admin, String> {
	
//	@Query("select c.courseCode,c.courseName,r.courseCode from Course c inner join RegisteredCourse r on c.courseCode = r.courseCode")
//	public abstract List<Object> viewCourses(@Param( value ="adminId") String adminId);
}

	//public List<Catalog> viewCourses();

	//public void setGeneratedReportCardTrue(String studentid);

	//public List<RegisteredCourse> generateGradeCard(String studentid);


