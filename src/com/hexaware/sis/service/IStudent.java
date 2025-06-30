package com.hexaware.sis.service;

import com.hexaware.sis.entity.Student;

public interface IStudent {
    void addStudent(Student student);
    void updateStudent(Student student);
    Student getStudentById(int studentId);
    void displayStudentDetails(int studentId);
    void deleteStudent(int studentId);
}
