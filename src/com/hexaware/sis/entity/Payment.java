package com.hexaware.sis.entity;

public class Payment {
    private int PaymentID;
    private int StudentID;
    private double Amount;
    private String PaymentDate;
    private Student student;

	// Getter for Payment ID
    public int getPaymentID() {
        return PaymentID;
    }

    // Setter for Payment ID
    public void setPaymentID(int paymentID) {
        PaymentID = paymentID;
    }

    // Getter for Student ID (who made the payment)
    public int getStudentID() {
        return StudentID;
    }

    // Setter for Student ID
    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    // Getter for payment amount
    public double getAmount() {
        return Amount;
    }

    // Setter for payment amount
    public void setAmount(double amount) {
        Amount = amount;
    }

    // Getter for payment date
    public String getPaymentDate() {
        return PaymentDate;
    }

    // Setter for payment date
    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }
     //getters and setters for student
    public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

    // Constructor 
    public Payment(int PaymentID, int StudentID, double Amount, String PaymentDate) {
        this.PaymentID = PaymentID;
        this.StudentID = StudentID;
        this.Amount = Amount;
        this.PaymentDate = PaymentDate;
    }
    
    //constructor for sis class
    public Payment(int PaymentID, double Amount, String PaymentDate, Student student) {
        this.PaymentID = PaymentID;
        this.StudentID = student.getStudentID(); // link StudentID
        this.Amount = Amount;
        this.PaymentDate = PaymentDate;
    }

}
