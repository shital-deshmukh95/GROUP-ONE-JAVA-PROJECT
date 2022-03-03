
package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lt.crs.model.Course;
import com.lt.crs.model.RegisteredCourse;

/**
 * @author user213
 *
 */
public interface RegisteredCourseRepository extends CrudRepository<RegisteredCourse, String> {

	@Query("select c from Course c inner join RegisteredCourse r on c.courseCode = r.courseCode where r.studentId =:studentId ")
	public List<Course> viewRegisteredCourses(@Param(value = "studentId")String studentId);

	int deleteByCourseCodeAndStudentId(String courseCode, String studentId);
	
	@Query(value = "select sum(courseFee) from course where courseCode in (select courseCode from registeredcourse where studentId=:studentId)",nativeQuery=true)
	public double calulateFee(@Param(value = "studentId")String studentId);

	public List<RegisteredCourse> findByCourseCodeAndStudentId(String courseID, String studentID);


}
