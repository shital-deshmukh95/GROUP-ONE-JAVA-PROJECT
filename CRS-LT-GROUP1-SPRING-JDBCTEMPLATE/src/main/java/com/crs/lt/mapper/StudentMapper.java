package com.crs.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crs.lt.bean.Student;
import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;
/**
 * 
 * @author user215
 * StudentMapper class to be use to map Student .
 */
public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Student student = new Student();
		//student.setUserId(rs.getString("userId"));
		student.setStudentId(rs.getString("studentId"));
		student.setRole(RoleConstant.stringToName(rs.getString("role")));
		student.setReportGenerated(rs.getBoolean("isReportGenerated"));
		student.setRegisterd(rs.getBoolean("isRegistered"));
		student.setPassword(rs.getString("password"));
		student.setPaid(rs.getBoolean("isPaid"));
		student.setName(rs.getString("name"));
		student.setGender(GenderConstant.stringToGender(rs.getString("gender")));
		student.setBranchName(rs.getString("branch"));
		student.setBatch(rs.getInt("batch"));
		student.setApproved(rs.getBoolean("isApproved"));
		student.setAddress(rs.getString("address"));
		return null;
	}

}
