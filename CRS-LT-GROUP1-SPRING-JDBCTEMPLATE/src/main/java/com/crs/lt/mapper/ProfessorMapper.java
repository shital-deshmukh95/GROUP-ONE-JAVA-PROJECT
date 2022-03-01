package com.crs.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crs.lt.bean.Professor;
/**
 * 
 * @author user215
 * ProfessorMapper class to be use to map Professor .
 */
public class ProfessorMapper implements RowMapper<Professor>
{
	@Override
	public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Professor professor = new Professor();
		professor.setProfessorID(rs.getString("professorID"));
		professor.setDepartment(rs.getString("department"));
		professor.setDesignation(rs.getString("designation"));
		return professor;
}
}