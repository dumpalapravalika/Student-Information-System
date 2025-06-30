package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Teacher;
import com.hexaware.sis.service.ITeacher;
import com.hexaware.sis.util.DBConnUtil;
import com.hexaware.sis.util.DBPropertyUtil;

import java.sql.*;
import java.util.Properties;

public class TeacherDAO implements ITeacher {
    Properties props = DBPropertyUtil.loadProperties("db.properties");

    @Override
    public void addTeacher(Teacher teacher) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "INSERT INTO Teacher (TeacherID, FirstName, LastName, Email) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teacher.getTeacherID());
            stmt.setString(2, teacher.getFirstName());
            stmt.setString(3, teacher.getLastName());
            stmt.setString(4, teacher.getEmail());
            stmt.executeUpdate();
            System.out.println("Teacher added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        Teacher teacher = null;
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Teacher WHERE TeacherID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(
                    rs.getInt("TeacherID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    "" // expertise field missing from DB as per latest schema
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "UPDATE Teacher SET FirstName = ?, LastName = ?, Email = ? WHERE TeacherID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getEmail());
            stmt.setInt(4, teacher.getTeacherID());
            stmt.executeUpdate();
            System.out.println("Teacher updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(int teacherId) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "DELETE FROM Teacher WHERE TeacherID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teacherId);
            stmt.executeUpdate();
            System.out.println("Teacher deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayTeacherDetails(int teacherId) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Teacher WHERE TeacherID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Teacher ID: " + rs.getInt("TeacherID"));
                System.out.println("Name: " + rs.getString("FirstName") + " " + rs.getString("LastName"));
                System.out.println("Email: " + rs.getString("Email"));
            } else {
                System.out.println("Teacher not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
