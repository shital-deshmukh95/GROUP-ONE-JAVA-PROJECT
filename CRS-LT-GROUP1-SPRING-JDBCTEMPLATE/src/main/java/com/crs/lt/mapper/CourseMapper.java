package com.crs.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crs.lt.bean.Course;

/**
 * 
 * @author user215
 * CourseMapper class to be use to map Course.
 */
public class CourseMapper implements RowMapper<Course> {

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Course course = new Course();
		course.setCourseCode(rs.getString("courseCode"));
		course.setCourseName(rs.getString("courseName"));
		course.setAvailable_seats(rs.getInt("availableSeats"));
		course.setInstructorId(rs.getString("instructorId"));
		return course;
	}

}
