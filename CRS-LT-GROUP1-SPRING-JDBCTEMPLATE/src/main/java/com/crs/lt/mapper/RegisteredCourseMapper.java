package com.crs.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crs.lt.bean.Course;
import com.crs.lt.bean.RegisteredCourse;
/**
 * 
 * @author user215
 * RegisteredCourseMapper class to be use to map RegisteredCourse .
 */
public class RegisteredCourseMapper implements RowMapper<RegisteredCourse> {

	@Override
	public RegisteredCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		RegisteredCourse course = new RegisteredCourse();
		course.setCourse(new Course(rs.getString("courseCode"), rs.getString("courseName"), rs.getString("instructorId"), rs.getInt("availableSeats")));
		course.setStudentId(rs.getString("studentId"));
		course.setGrade(rs.getString("grade"));
		return course;
	}

}
