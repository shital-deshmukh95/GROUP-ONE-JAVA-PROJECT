package com.crs.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crs.lt.bean.Admin;
import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;

public class AdminMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		admin.setUserId(rs.getString("userId"));
		admin.setPassword(rs.getString("password"));
		admin.setName(rs.getString("name"));
		admin.setGender(GenderConstant.stringToGender(rs.getString("gender")));
		admin.setAddress("address");
		admin.setRole(RoleConstant.stringToName(rs.getString("role")));
		
		return admin;
	}

}
