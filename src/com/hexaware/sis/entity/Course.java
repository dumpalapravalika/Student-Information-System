package com.hexaware.sis.entity;

import java.util.List;
import java.util.ArrayList;

public class Course {
	private int CourseID;
	private String CourseName;
	private String CourseCode;
	private String InstructorName;
	private Teacher teacher; // Reference to the assigned Teacher

	// List to keep track of students enrolled in this course
	private List<Enrollment> enrollments = new ArrayList<>();

	// Getter and setter for Teacher
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	// Getter and setter for list of enrollments
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	// Getter and setter for CourseID
	public int getCourseID() {
		return CourseID;
	}
	public void setCourseID(int courseID) {
		CourseID = courseID;
	}

	// Getter and setter for Course Name
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	// Getter and setter for Course Code
	public String getCourseCode() {
		return CourseCode;
	}
	public void setCourseCode(String courseCode) {
		CourseCode = courseCode;
	}

	// Getter and setter for Instructor Name (string)
	public String getInstructorName() {
		return InstructorName;
	}
	public void setInstructorName(String instructorName) {
		InstructorName = instructorName;
	}

	// Constructor 
	public Course(int CourseID, String CourseName, String CourseCode, String InstructorName) {
		this.CourseID = CourseID;
		this.CourseName = CourseName;
		this.CourseCode = CourseCode;
		this.InstructorName = InstructorName;
	}

	// Method to assign a teacher to this course
	public void assignTeacher(Teacher teacher) {
		this.teacher = teacher;          // Link the teacher object to this course
		teacher.addCourse(this);         // Also add this course to the teacher’s assigned courses
		System.out.println("Teacher " + teacher.getFirstName() + " Assigned course " + CourseName);
	}

	// Method to update course information
	public void updateCourseInfo(String CourseCode, String CourseName, String InstructorName) {
		this.CourseCode = CourseCode;
		this.CourseName = CourseName;
		this.InstructorName = InstructorName;
		System.out.println("updated course info");
	}

	// Method to display course details
	public void displayCourseInfo() {
		System.out.println("CourseID: " + CourseID);
		System.out.println("Course Name " + CourseName);
		System.out.println("Course Code: " + CourseCode);
		
		if (teacher != null) {
			// If teacher is assigned, show full name
			System.out.println("Instructor: " + teacher.getFirstName() + " " + teacher.getLastName());
		} else {
			System.out.println("Instructor: Not Assigned");
		}
	}

	// Method to add an enrollment (student registration) to this course
	public void addEnrollment(Enrollment enrollment) {
		enrollments.add(enrollment);
	}
}
