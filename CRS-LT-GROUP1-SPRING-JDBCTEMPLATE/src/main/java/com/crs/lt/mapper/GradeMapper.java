package com.crs.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crs.lt.bean.Grade;
/**
 * 
 * @author user215
 * GradeMapper class to be use to map Grade.
 */
public class GradeMapper implements RowMapper<Grade> {

	@Override
	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Grade grade = new Grade();
		grade.setCourseCode(rs.getString("courseCode"));
		grade.setCourseName(rs.getString("courseName"));
		grade.setGrade(rs.getString("grade"));
		return grade;
	}

}
