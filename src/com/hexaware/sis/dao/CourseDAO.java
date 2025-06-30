package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Course;
import com.hexaware.sis.service.ICourse;
import com.hexaware.sis.util.DBConnUtil;
import com.hexaware.sis.util.DBPropertyUtil;

import java.sql.*;
import java.util.Properties;

public class CourseDAO implements ICourse {
    Properties props = DBPropertyUtil.loadProperties("db.properties");

    @Override
    public void addCourse(Course course) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "INSERT INTO Course (CourseID, CourseName, CourseCode, InstructorName) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, course.getCourseID());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getCourseCode());
            stmt.setString(4, course.getInstructorName());
            stmt.executeUpdate();
            System.out.println("Course inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void displayCourseDetails(int courseId) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Course WHERE CourseID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Course ID: " + rs.getInt("CourseID"));
                System.out.println("Course Name: " + rs.getString("CourseName"));
                System.out.println("Course Code: " + rs.getString("CourseCode"));
                System.out.println("Instructor: " + rs.getString("InstructorName"));
            } else {
                System.out.println("Course not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Course getCourseById(int courseId) {
        Course course = null;
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Course WHERE CourseID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                course = new Course(
                        rs.getInt("CourseID"),
                        rs.getString("CourseName"),
                        rs.getString("CourseCode"),
                        rs.getString("InstructorName")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "UPDATE Course SET CourseName=?, CourseCode=?, InstructorName=? WHERE CourseID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setString(3, course.getInstructorName());
            stmt.setInt(4, course.getCourseID());
            stmt.executeUpdate();
            System.out.println("Course updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "DELETE FROM Course WHERE CourseID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, courseId);
            stmt.executeUpdate();
            System.out.println("Course deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Course getCourseByCode(String courseCode) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Course WHERE CourseCode = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, courseCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("CourseID");
                String name = rs.getString("CourseName");
                String code = rs.getString("CourseCode");
                String instructor = rs.getString("InstructorName");
                return new Course(id, name, code, instructor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
