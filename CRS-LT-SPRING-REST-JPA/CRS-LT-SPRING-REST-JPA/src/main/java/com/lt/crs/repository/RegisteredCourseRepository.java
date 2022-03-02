
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

	@Query(value = "select * from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId =:studentId " ,nativeQuery = true)
	List<Course> viewRegisteredCourses(@Param(value = "studentId")String studentId);

	int deleteByCourseCodeAndStudentId(String courseCode, String studentId);
	

}
