package com.crs.lt.business;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.lt.bean.Student;
import com.crs.lt.constant.GenderConstant;
import com.crs.lt.constant.RoleConstant;
import com.crs.lt.dao.StudentDAOInterface;
import com.crs.lt.dao.StudentDaoOperation;
import com.crs.lt.exceptions.StudentNotRegisteredException;

@Service
public class StudentService implements StudentInterface {
	private static Logger logger = Logger.getLogger(StudentService.class);
	
	@Autowired
	private StudentDaoOperation studentDaoInterface;
	//StudentDAOInterface studentDaoInterface=new StudentDaoOperation();

	@Override
	public String register(String name,String userId,String password,GenderConstant gender,int batch,String branch,String address) throws StudentNotRegisteredException, SQLException {
		// TODO Auto-generated method stub
		String studentId;
		try {
			
			Student newStudent=new Student(userId,name,RoleConstant.STUDENT,password,gender,address,branch,userId,batch,false);
			System.out.println("\nYour account has been created\n");
			studentId=studentDaoInterface.addStudent(newStudent);
		}
			catch (StudentNotRegisteredException e) {
				throw e;
			}
			
			
		return studentId;
	}

	@Override
	public String getStudentId(String userId) throws SQLException {
		// TODO Auto-generated method stub
		 return studentDaoInterface.getStudentId(userId);
	}

	@Override
	public boolean isApproved(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return studentDaoInterface.isApproved(studentId);
	}
	

}
