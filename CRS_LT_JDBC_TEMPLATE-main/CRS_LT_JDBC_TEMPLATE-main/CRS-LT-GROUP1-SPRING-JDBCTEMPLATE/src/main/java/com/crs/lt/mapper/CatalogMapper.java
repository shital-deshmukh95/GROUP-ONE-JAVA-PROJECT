package com.crs.lt.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crs.lt.bean.Catalog;

public class CatalogMapper implements RowMapper<Catalog> {

	@Override
	public Catalog mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Catalog catalog = new Catalog();
		catalog.setCourseCode(rs.getString("courseCode"));
		catalog.setCourseName(rs.getString("courseName"));
		catalog.setDescription(rs.getString("description"));
		return catalog;
	}

}
