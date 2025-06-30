package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Enrollment;
import com.hexaware.sis.service.IEnrollment;
import com.hexaware.sis.util.DBConnUtil;
import com.hexaware.sis.util.DBPropertyUtil;

import java.sql.*;
import java.util.Properties;

public class EnrollmentDAO implements IEnrollment {
    Properties props = DBPropertyUtil.loadProperties("db.properties");

    @Override
    public void enrollStudent(Enrollment enrollment) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "INSERT INTO Enrollment (EnrollmentID, StudentID, CourseID, EnrollmentDate) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, enrollment.getEnrollmentID());
            stmt.setInt(2, enrollment.getStudentID());
            stmt.setInt(3, enrollment.getCourseID());
            stmt.setDate(4, Date.valueOf(enrollment.getEnrollmentDate()));
            stmt.executeUpdate();
            System.out.println("Student enrolled successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {
        Enrollment enrollment = null;
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Enrollment WHERE EnrollmentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, enrollmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                enrollment = new Enrollment(
                    rs.getInt("EnrollmentID"),
                    rs.getInt("StudentID"),
                    rs.getInt("CourseID"),
                    rs.getDate("EnrollmentDate").toString(),
                    null,
                    null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollment;
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "UPDATE Enrollment SET StudentID = ?, CourseID = ?, EnrollmentDate = ? WHERE EnrollmentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, enrollment.getStudentID());
            stmt.setInt(2, enrollment.getCourseID());
            stmt.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            stmt.setInt(4, enrollment.getEnrollmentID());
            stmt.executeUpdate();
            System.out.println("Enrollment updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEnrollment(int enrollmentId) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "DELETE FROM Enrollment WHERE EnrollmentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, enrollmentId);
            stmt.executeUpdate();
            System.out.println("Enrollment deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayEnrollmentDetails(int enrollmentId) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Enrollment WHERE EnrollmentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, enrollmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Enrollment ID: " + rs.getInt("EnrollmentID"));
                System.out.println("Student ID: " + rs.getInt("StudentID"));
                System.out.println("Course ID: " + rs.getInt("CourseID"));
                System.out.println("Date: " + rs.getDate("EnrollmentDate"));
            } else {
                System.out.println("Enrollment not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}