package com.hexaware.sis.entity;

public class Enrollment {
	private int EnrollmentID;
	private int StudentID; // reference to sudent
	private int CourseID; // reference to course
	private String EnrollmentDate;
	private Student student; // object reference to student
	private Course course; // object reference to course

	// Getter and setter for Enrollment ID
	public int getEnrollmentID() {
		return EnrollmentID;
	}
	public void setEnrollmentID(int enrollmentID) {
		EnrollmentID = enrollmentID;
	}

	// Getter and setter for Student ID
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	// Getter and setter for Course ID
	public int getCourseID() {
		return CourseID;
	}
	public void setCourseID(int courseID) {
		CourseID = courseID;
	}

	// Getter and setter for Enrollment Date
	public String getEnrollmentDate() {
		return EnrollmentDate;
	}
	public void setEnrollmentDate(String enrollmentDate) {
		EnrollmentDate = enrollmentDate;
	}

	// Constructor 
	public Enrollment(int EnrollmentID, int StudentID, int CourseID, String EnrollmentDate, Student student, Course course) {
		this.EnrollmentID = EnrollmentID;
		this.StudentID = StudentID;
		this.CourseID = CourseID;
		this.EnrollmentDate = EnrollmentDate;
		this.student = student;
		this.course = course;
	}

	
	public Enrollment(int EnrollmentID, String EnrollmentDate, Student student, Course course) {
	    this.EnrollmentID = EnrollmentID;
	    this.EnrollmentDate = EnrollmentDate;
	    this.student = student;
	    this.course = course;
	}


	// Constructor using only object references for sis class
	public Enrollment(int EnrollmentID, Student student, Course course, String EnrollmentDate) {
        this.EnrollmentID = EnrollmentID;
        this.student = student;
        this.course = course;
        this.EnrollmentDate = EnrollmentDate;
	}

	// Returns the student object associated with this enrollment
	public Student getStudent() {
		return student;
	}

	// Returns the course object associated with this enrollment
	public Course getCourse() {
		return course;
	}
}


