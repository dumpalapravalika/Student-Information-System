package com.hexaware.sis.entity;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private int teacherID;               
    private String firstName;           
    private String lastName;            
    private String email;               
    private String expertise;           

    private List<Course> assignedCourses = new ArrayList<>(); // List of courses assigned to the teacher

    //Constructor
       public Teacher(int teacherID, String firstName, String lastName, String email, String expertise) {
        this.teacherID = teacherID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.expertise = expertise;
    }

    //Getters and Setters

    public int getTeacherID() {
        return teacherID; 
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;  
    }

    public String getFirstName() {
        return firstName;  
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;  
    }

    public String getLastName() {
        return lastName;  
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;  
    }

    public String getEmail() {
        return email;  
    }

    public void setEmail(String email) {
        this.email = email; 
    }

    public String getExpertise() {
        return expertise;  
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;  
    }

    //Methods

    // Adds a course to the list of courses assigned to the teacher
    public void addCourse(Course course) {
        assignedCourses.add(course);
    }

    // Updates teacher information
    public void updateTeacherInfo(String firstName, String lastName, String email, String expertise) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.expertise = expertise;
    }

    // Displays teacher details
    public void displayTeacherInfo() {
        System.out.println("Teacher ID: " + teacherID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Expertise: " + expertise);
    }

    // Returns the list of courses assigned to the teacher
    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }
}
