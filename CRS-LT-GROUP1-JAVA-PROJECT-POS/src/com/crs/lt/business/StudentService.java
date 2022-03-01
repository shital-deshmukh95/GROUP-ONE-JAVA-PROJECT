package com.crs.lt.business;

import org.apache.log4j.Logger;

import com.crs.lt.bean.Student;
import com.crs.lt.doa.StudentDAOInterface;
import com.crs.lt.doa.StudentDaoOperation;
import com.crs.lt.exceptions.StudentNotRegisteredException;

public class StudentService implements StudentInterface {
	private static Logger logger = Logger.getLogger(StudentService.class);
	StudentDAOInterface studentDaoInterface=new StudentDaoOperation();

	@Override
	public String register(String name,String userId,String password,String gender,int batch,String branch,String address) throws StudentNotRegisteredException {
		// TODO Auto-generated method stub
		String studentId;
		try {
			String role = "STUDENT";
			Student newStudent=new Student(userId,name,role,password,gender,address,branch,userId,batch,false);
			System.out.println("\nYour account has been created\n");
			studentId=studentDaoInterface.addStudent(newStudent);
		}
			catch (StudentNotRegisteredException e) {
				throw e;
			}
			
			
		return studentId;
	}

	@Override
	public String getStudentId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isApproved(String studentId) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
