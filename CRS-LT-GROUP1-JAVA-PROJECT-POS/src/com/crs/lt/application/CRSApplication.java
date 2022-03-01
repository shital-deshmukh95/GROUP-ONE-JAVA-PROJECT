package com.crs.lt.application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;
import java.util.Scanner;

import com.crs.lt.business.StudentInterface;
import com.crs.lt.business.StudentService;
import com.crs.lt.business.UserInterface;
import com.crs.lt.business.UserService;
import com.crs.lt.exceptions.CourseNotDeletedException;
import com.crs.lt.exceptions.CourseNotFoundException;
import com.crs.lt.exceptions.GradeNotAddedException;
import com.crs.lt.exceptions.SeatNotAvailableException;
import com.crs.lt.exceptions.StudentNotRegisteredException;
import com.crs.lt.exceptions.UserNotFoundException;
import com.crs.lt.validator.StringUtils;



/**
 * @author user203
 *
 */
public class CRSApplication {

	/**
	 * @param args
	 */
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static final String ANSI_BOLD = "\u001B[1m ";
	static boolean verified = false;
	UserInterface userInterface = new UserService();
	StudentInterface studentInterface = new StudentService();
	
	public static LocalDate localDate = LocalDate.now();
	public static LocalTime localTime = LocalTime.now();
	public static Month month = localDate.getMonth();
	public static int date = localDate.getDayOfMonth();
	public static int year = localDate.getYear();
	public static int second =localTime.getSecond();
	public static int minute = localTime.getMinute();
	public static int hour = localTime.getHour();
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CRSApplication crs = new CRSApplication();
		
		Scanner scanner = new Scanner(System.in);
		int x = 0;
		crsMenu();
		x = scanner.nextInt();
		try {
			while(x!=4)
			{
				switch(x){
				case 1:
					crs.loginUser();
					break;
				case 2:
					crs.RegisterStudent();
					break;
				case 3:
					crs.updatePassword();
					break;
				default:
					System.out.println("The session ended");
				}
				//crsMenu();
				//x = scanner.nextInt();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error occured");
			e.printStackTrace();
		}
		finally{
			scanner.close();
		}
		
	}
	

	public static void crsMenu() {
		
		System.out.println(ANSI_BOLD + ANSI_BLUE+StringUtils.center("Welcome to the CRS Application", 75) + ANSI_RESET);
		System.out.println(ANSI_RED+StringUtils.center("Logged in date: "+ANSI_RESET +date+ " "+ month+" " + year, 78));
		System.out.println(ANSI_RED+StringUtils.center("Logged in time: "+ANSI_RESET+hour+ ":"+ minute+":" + second, 78));
		System.out.println(StringUtils.center("************************************************************************************************",100));
		System.out.println(ANSI_PURPLE+"1. Login");
		System.out.println("2. Student Registration");
		System.out.println("3. Update password");
		System.out.println("4. Exit" +ANSI_RESET);
		System.out.println(StringUtils.center("************************************************************************************************",100));
		
	}
	public void updatePassword() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String userId,newPassword;
		try {
			System.out.println(ANSI_GREEN+"------------------Update Password--------------------"+ANSI_RESET);
			System.out.println("User ID");
			userId=in.next();
			System.out.println("New Password:");
			newPassword=in.next();
			boolean isUpdated=userInterface.updatePassword(userId, newPassword);
			if(isUpdated)
				System.out.println("Password updated successfully!");

			else
				System.out.println("Something went wrong, please try again!");
		} catch(Exception ex) {
			System.out.println("Error Occured "+ex.getMessage());
		}
	}

	public void RegisterStudent() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);

		String userId,name,password,address,branchName,gender;
		int batch, genderC;
		try {

			//input all the student details
			System.out.println(ANSI_GREEN+"---------------Student Registration-------------"+ANSI_RESET);
			System.out.println("Name:");
			name=sc.nextLine();
			System.out.println("UserID:");
			userId=sc.next();
			System.out.println("Password:");
			password=sc.next();
			sc.nextLine();
			System.out.println("Gender: \t 1: M \t 2: F\t 3: Other");
			genderC=sc.nextInt();
			sc.nextLine();
			
			switch(genderC)
			{
			case 1:
				gender="M";
				break;
			case 2:
				gender="F";
				break;
				
			case 3:
				gender="Other";
				break;
			default: 
				gender="Other";
			}
		
			System.out.println("Branch:");
			branchName=sc.next();
			System.out.println("Batch:");
			batch=sc.nextInt();
			
			System.out.println("Address:");
			address=sc.next();
			
			
			String newStudentId = studentInterface.register(name, userId, password,gender, batch, branchName, address);
			
			//notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, newStudentId, null,0);
			
		
		}
		
	catch(StudentNotRegisteredException ex)
		{
			System.out.println("Something went wrong! "+ex.getStudentName() +" not registered. Please try again");
		}
	}

	public void loginUser() throws SQLException, CourseNotFoundException, CourseNotDeletedException, GradeNotAddedException, UserNotFoundException, SeatNotAvailableException {
		// TODO Auto-generated method stub
		Scanner user = new Scanner(System.in);
		String userId, password;
		try {
			System.out.println(ANSI_GREEN+"---------------Login To CRS-------------------"+ANSI_RESET);
			
			System.out.println("UserId:");
			userId = user.next();
			System.out.println("Password:");
			password = user.next();
			//Verify Cerds of user
			
			verified = userInterface.verifyCredentials(userId, password);
			//System.out.println("User is verified" + verified);
			
			if(verified){
				String role = userInterface.getRole(userId);
				String name = userInterface.getName(userId);
				System.out.println(StringUtils.center("************************************************************************************************",100));

				System.out.println(ANSI_BOLD+ANSI_BLUE+StringUtils.center("Welcome " +name + "!!!"+ANSI_RESET,75));
				System.out.println(StringUtils.center("************************************************************************************************",100));

				switch(role){
				case "STUDENT":
					
					StudentCRSApplication studentCRSApplication = new StudentCRSApplication();
					studentCRSApplication.studentMenu(userId);
					break;
				case "PROFESSOR":
					//System.out.println("The role of user is Professor");
					
					ProfessorCRSApplication professorCRSApplication = new ProfessorCRSApplication();
					professorCRSApplication.createMenu(userId);
					break;
				case "ADMIN":
					//System.out.println("The role of user is Admin");
					
					AdminCRSApplication adminCRSApplication = new AdminCRSApplication();
					adminCRSApplication.createMenu();
					break;
				}
			}
			else
				System.out.println("Kindly check the userId and password");
		}
		catch (UserNotFoundException e){
			System.out.println(e.getMessage());
		}
		
	}

}
