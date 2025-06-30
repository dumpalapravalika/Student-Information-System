package com.hexaware.sis.service;

import com.hexaware.sis.entity.Teacher;

public interface ITeacher {
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    Teacher getTeacherById(int teacherId);
    void displayTeacherDetails(int teacherId);
    void deleteTeacher(int teacherId);
}
