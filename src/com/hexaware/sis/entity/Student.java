package com.hexaware.sis.entity;

import java.util.List;
import java.util.ArrayList;

public class Student {
    private int StudentID;               
    private String FirstName;           
    private String LastName;          
    private String DateofBirth;           
    private String Email;               
    private String PhoneNumber;         

    private List<Enrollment> enrollments = new ArrayList<>(); // List of courses the student enrolled in
    private List<Payment> PaymentHistory = new ArrayList<>(); // List of payments made by the student

    //Getters and Setters
    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDateofBirth() {
        return DateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        DateofBirth = dateofBirth;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    // Constructor
    public Student(int StudentID, String FirstName, String LastName, String DateofBirth, String Email, String PhoneNumber) {
        this.StudentID = StudentID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.DateofBirth = DateofBirth;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
    }

    //Methods

    // Enrolls the student in a course by adding it to their enrolledCourses list
    	public void enrollInCourse(Course course) {
    	    Enrollment enrollment = new Enrollment(enrollments.size() + 1, new String(), this, course);
    	    enrollments.add(enrollment);
    	    System.out.println(FirstName + " enrolled in " + course.getCourseName());
    	}
    // Updates the student's information
    public void updateStudentInfo(String FirstName, String LastName, String DateofBirth, String Email, String PhoneNumber) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.DateofBirth = DateofBirth;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        System.out.println("Updated student info");
    }

    // Records a payment made by the student
    public void makePayment(double amount, String paymentdate) {
        Payment payment = new Payment(PaymentHistory.size() + 1, StudentID, amount, paymentdate);
        PaymentHistory.add(payment);
        System.out.println("Payment of " + amount + " recorded on " + paymentdate);
    }

    // Displays student's full details
    public void displaystudentinfo() {
        System.out.println("Student ID: " + StudentID);
        System.out.println("Name: " + FirstName + " " + LastName);
        System.out.println("DOB: " + DateofBirth);
        System.out.println("Email: " + Email);
        System.out.println("Phone: " + PhoneNumber);
    }

    // Returns the list of courses the student is enrolled in
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    // Returns the list of payments made by the student
    public List<Payment> getPaymentHistory() {
        return PaymentHistory;
    }
}
