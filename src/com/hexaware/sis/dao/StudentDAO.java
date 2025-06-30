package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Student;
import com.hexaware.sis.service.IStudent;
import com.hexaware.sis.util.DBConnUtil;
import com.hexaware.sis.util.DBPropertyUtil;

import java.sql.*;
import java.util.Properties;

public class StudentDAO implements IStudent {

    Properties props = DBPropertyUtil.loadProperties("db.properties");

    @Override
    public void addStudent(Student student) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "INSERT INTO Student (StudentID, FirstName, LastName, DateOfBirth, Email, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, student.getStudentID());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setDate(4, Date.valueOf(student.getDateofBirth())); // Format: yyyy-MM-dd
            stmt.setString(5, student.getEmail());
            stmt.setString(6, student.getPhoneNumber());

            int rows = stmt.executeUpdate();
            System.out.println(rows + " student(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int studentId) {
        Student student = null;
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Student WHERE StudentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                student = new Student(
                    rs.getInt("StudentID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("DateOfBirth"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "UPDATE Student SET FirstName=?, LastName=?, DateOfBirth=?, Email=?, PhoneNumber=? WHERE StudentID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setDate(3, Date.valueOf(student.getDateofBirth()));
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getPhoneNumber());
            stmt.setInt(6, student.getStudentID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "DELETE FROM Student WHERE StudentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayStudentDetails(int studentId) {
        Student s = getStudentById(studentId);
        if (s != null) {
            System.out.println("ID: " + s.getStudentID());
            System.out.println("Name: " + s.getFirstName() + " " + s.getLastName());
            System.out.println("DOB: " + s.getDateofBirth());
            System.out.println("Email: " + s.getEmail());
            System.out.println("Phone: " + s.getPhoneNumber());
        } else {
            System.out.println("Student not found");
        }
    }
}
