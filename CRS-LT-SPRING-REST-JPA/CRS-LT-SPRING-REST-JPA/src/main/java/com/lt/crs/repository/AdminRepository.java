package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lt.crs.model.Admin;
import com.lt.crs.model.Catalog;
import com.lt.crs.model.RegisteredCourse;


public interface AdminRepository extends CrudRepository<Admin, String> {

	//public List<Catalog> viewCourses();

	//public void setGeneratedReportCardTrue(String studentid);

	//public List<RegisteredCourse> generateGradeCard(String studentid);


}