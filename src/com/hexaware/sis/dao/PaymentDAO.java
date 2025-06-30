package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Payment;
import com.hexaware.sis.service.IPayment;
import com.hexaware.sis.util.DBConnUtil;
import com.hexaware.sis.util.DBPropertyUtil;

import java.sql.*;
import java.util.Properties;

public class PaymentDAO implements IPayment {
    Properties props = DBPropertyUtil.loadProperties("db.properties");

    @Override
    public void makePayment(Payment payment) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "INSERT INTO Payment (PaymentID, StudentID, Amount, PaymentDate) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, payment.getPaymentID());
            stmt.setInt(2, payment.getStudentID());
            stmt.setDouble(3, payment.getAmount());
            stmt.setDate(4, Date.valueOf(payment.getPaymentDate()));
            stmt.executeUpdate();
            System.out.println("Payment recorded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        Payment payment = null;
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Payment WHERE PaymentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, paymentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                payment = new Payment(
                    rs.getInt("PaymentID"),
                    rs.getInt("StudentID"),
                    rs.getDouble("Amount"),
                    rs.getDate("PaymentDate").toString()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public void updatePayment(Payment payment) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "UPDATE Payment SET StudentID = ?, Amount = ?, PaymentDate = ? WHERE PaymentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, payment.getStudentID());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.setInt(4, payment.getPaymentID());
            stmt.executeUpdate();
            System.out.println("Payment updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePayment(int paymentId) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "DELETE FROM Payment WHERE PaymentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, paymentId);
            stmt.executeUpdate();
            System.out.println("Payment deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayPaymentDetails(int paymentId) {
        try (Connection conn = DBConnUtil.getConnection(props)) {
            String query = "SELECT * FROM Payment WHERE PaymentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, paymentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Payment ID: " + rs.getInt("PaymentID"));
                System.out.println("Student ID: " + rs.getInt("StudentID"));
                System.out.println("Amount: " + rs.getDouble("Amount"));
                System.out.println("Date: " + rs.getDate("PaymentDate"));
            } else {
                System.out.println("Payment not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
