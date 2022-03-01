package com.crs.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crs.lt.bean.EnrolledStudent;

/**
 * 
 * @author user215
 * EnrolledStudentMapper class to be use to map EnrolledStudent .
 */
public class EnrolledStudentMapper implements RowMapper<EnrolledStudent>
{

	@Override
	public EnrolledStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		EnrolledStudent enrolledStudent = new EnrolledStudent();
		enrolledStudent.setCourseCode(rs.getString("courseCode"));
		enrolledStudent.setCourseName(rs.getString("courseName"));
		enrolledStudent.setStudentId(rs.getString("studentId"));
		
		return enrolledStudent;
}

}