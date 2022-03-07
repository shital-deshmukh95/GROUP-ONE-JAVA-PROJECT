package com.lt.crs.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
* @author user214
*
*/
@Entity
@Table(name = "course")
public class Course {

@Id
private String courseCode;

@Column(name = "courseName")
private String courseName;

@Column (name = "instructorId")
private String instructorId;


@Column(name = "availableSeats")
private int availableSeats;

@Column(name = "courseFee")
private double courseFee;



public Course() {
super();
// TODO Auto-generated constructor stub
}



public Course(String courseCode, String courseName, String instructorId, int availableSeats, double courseFee) {
super();
this.courseCode = courseCode;
this.courseName = courseName;
this.instructorId = instructorId;
this.availableSeats = availableSeats;
this.courseFee = courseFee;
}



public String getCourseCode() {
return courseCode;
}



public void setCourseCode(String courseCode) {
this.courseCode = courseCode;
}



public String getCourseName() {
return courseName;
}



public void setCourseName(String courseName) {
this.courseName = courseName;
}



public String getInstructorId() {
return instructorId;
}



public void setInstructorId(String instructorId) {
this.instructorId = instructorId;
}



public int getAvailableSeats() {
return availableSeats;
}



public void setAvailableSeats(int availableSeats) {
this.availableSeats = availableSeats;
}



public double getCourseFee() {
return courseFee;
}



public void setCourseFee(double courseFee) {
this.courseFee = courseFee;
}



@Override
public String toString() {
return "Course [courseCode=" + courseCode + ", courseName=" + courseName + ", instructorId=" + instructorId
+ ", availableSeats=" + availableSeats + ", courseFee=" + courseFee + "]";
}

}
