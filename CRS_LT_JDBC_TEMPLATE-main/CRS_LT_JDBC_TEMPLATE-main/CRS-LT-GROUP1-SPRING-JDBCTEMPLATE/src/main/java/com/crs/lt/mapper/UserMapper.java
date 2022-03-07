package com.crs.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crs.lt.bean.Admin;
import com.crs.lt.bean.User;
import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getString("userId"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setGender(GenderConstant.stringToGender(rs.getString("gender")));
		user.setAddress("address");
		user.setRole(RoleConstant.stringToName(rs.getString("role")));
		
		
		return user;
	}
	
	

}
