package com.hexaware.sis.service;

import com.hexaware.sis.entity.Enrollment;

public interface IEnrollment {
    void enrollStudent(Enrollment enrollment);
    void updateEnrollment(Enrollment enrollment);
    Enrollment getEnrollmentById(int enrollmentId);
    void displayEnrollmentDetails(int enrollmentId);
    void deleteEnrollment(int enrollmentId);
}
