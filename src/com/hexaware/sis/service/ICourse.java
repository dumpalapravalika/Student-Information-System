package com.hexaware.sis.service;

import com.hexaware.sis.entity.Course;

	public interface ICourse {
	    void addCourse(Course course);
	    Course getCourseById(int courseId);
	    void updateCourse(Course course);
	    void deleteCourse(int courseId);
	    void displayCourseDetails(int courseId);
	}

