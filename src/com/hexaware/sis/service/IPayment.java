package com.hexaware.sis.service;

import com.hexaware.sis.entity.Payment;

public interface IPayment {
    void makePayment(Payment payment);
    void updatePayment(Payment payment);
    Payment getPaymentById(int paymentId);
    void displayPaymentDetails(int paymentId);
    void deletePayment(int paymentId);
}

