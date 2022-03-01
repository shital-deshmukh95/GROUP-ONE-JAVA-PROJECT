package com.crs.lt.constant;

public class SQLQueryConstant {

	public static final String CHECK_CREDENTIALS_USER_DETAILS = "select password from user where userId = ?";
	public static final String GET_ROLE_TYPE ="select role from user where userId = ?";
	public static final String ADD_COURSE_FOR_STUDENT ="insert into registeredcourse (studentId,courseCode,grade) values ( ? , ?, ?)";
	public static final String DECREMENT_AVAILABLE_SEATS ="update course set availableSeats = availableSeats-1 where courseCode = ?";
	public static final String SET_REGISTRATION_STATUS ="update student set isRegistered = true  where studentId=?";
	public static final String GET_REGISTRATION_STATUS ="select isRegistered from student where studentId = ?";
	public static final String GET_PAYMENT_STATUS="select isPaid from student where studentId = ?";
	public static final String VIEW_REGISTRATION_COURSE="select * from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?";
	public static final String VIEW_COURSE ="select * from course where courseCode not in  (select courseCode  from registeredcourse where studentId = ?) and availableSeats > 0";
	public static final String DROP_COURSE="delete from registeredcourse where courseCode = ? AND studentId = ?";
	public static final String INCREMENT_AVAILABLE_SEATS ="update course set availableSeats = availableSeats + 1 where  courseCode = ?";
	public static final String GET_ENROLLED_STUDENT ="select course.courseCode,course.courseName,registeredcourse.studentId from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where course.instructorId = ? order by course.courseCode";
	public static final String VIEW_COURSE_CATALOG ="select courseCode, courseName, description from Catalog";
	public static final String DELETE_COURSE_FROM_CATALOG = "delete from Course where courseCode = ?";
	public static final String ADD_COURSE_CATALOG ="insert into Catalog(courseCode, courseName, description) values (?, ?, ?)";
	public static final String ADD_GRADE="update registeredcourse set grade=? where courseCode=? and studentId=?";
	public static final String VIEW_GRADE = "select course.courseCode,course.courseName,registeredcourse.grade from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?";
	public static final String UPDATE_PASSWORD = "update user set password=? where userId = ? ";
	public static final String GET_SEATS = "select availableSeats from course where courseCode = ?";
	public static final String ADD_USER_QUERY = "insert into User(userId, name, password, role, gender, address) values (?, ?, ?, ?, ?, ?)";
	public static final String ADD_STUDENT = "insert into student (studentId,branch,batch,isApproved,isRegistered,isReportGenerated,isPaid) values (?,?,?,0,0,0,0)";
	public static final String CALCULATE_FEES = "select sum(courseFee) from course where courseCode in (select courseCode from registeredcourse where studentId = ?)";
	public static final String SET_PAYMENT_STATUS = "update student set isPaid = true  where studentId=?";
	public static final String GET_GENERATED_REPORT_CARD_TRUE = "select isReportGenerated from student where studentId = ?";
	public static final String SET_GENERATED_REPORT_CARD_TRUE = "update student set isReportGenerated = 1 where studentId = ?";
	public static final String GET_USER_NAME = "select name from user where userId = ?";
	public static final String IS_APPROVED="select isApproved from student where studentId = ? ";
	public static final String GET_STUDENT_ID="select studentId from student where studentId = ? ";
	public static final String VIEW_PENDING_ADMISSION_QUERY = "select userId,name, password, role, gender, address, studentId from user, student where isApproved = 0 and studentId = userId";
	public static final String APPROVE_STUDENT_QUERY = "update Student set isApproved = 1 where studentId = ?";
	public static final String ADD_PROFESSOR_QUERY = "insert into Professor(instructorId, department, designation) values (?, ?, ?)";
	public static final String ASSIGN_COURSE_QUERY = "update Course set instructorId = ? where courseCode = ?";
	public static final String GET_PROFESSOR_BY_USER_ID = "SELECT professor.instructorId, user.name, user.userId, professor.department, professor.designation FROM professor INNER JOIN user ON professor.instructorId = user.userId WHERE userId = ?";
	public static final String GET_PROF_NAME = "select name from user where userId = ?";
	
	
	
}
