package com.hexaware.sis.main;

import com.hexaware.sis.entity.*;
import com.hexaware.sis.dao.*;
import java.util.Scanner;
import com.hexaware.sis.util.*;

import java.sql.Connection;
import java.util.Properties;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
                Properties props = DBPropertyUtil.loadProperties("db.properties");
                Connection conn = DBConnUtil.getConnection(props);

                if (conn != null) {
                    System.out.println("Connected to database!");
                } else {
                    System.out.println("Failed to connect.");
                }
          
      
        StudentDAO studentDAO = new StudentDAO();
        TeacherDAO teacherDAO = new TeacherDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        PaymentDAO paymentDAO = new PaymentDAO();

        while (true) {
            System.out.println("\n====== STUDENT INFORMATION SYSTEM ======");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");

            System.out.println("5. Add Course");
            System.out.println("6. View Course");

            System.out.println("7. Add Teacher");
            System.out.println("8. View Teacher");

            System.out.println("9. Enroll Student");
            System.out.println("10. Assign Teacher");

            System.out.println("11. View Enrollment");
            System.out.println("12. Make Payment");

            System.out.println("13. View Payment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    System.out.println("Enter Student Details:");
                    System.out.print("ID: ");
                    int sid = sc.nextInt(); sc.nextLine();
                    System.out.print("First Name: ");
                    String fname = sc.nextLine();
                    System.out.print("Last Name: ");
                    String lname = sc.nextLine();
                    System.out.print("DOB (yyyy-mm-dd): ");
                    String dob = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    Student s = new Student(sid, fname, lname, dob, email, phone);
                    studentDAO.addStudent(s);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    int vsid = sc.nextInt();
                    studentDAO.displayStudentDetails(vsid);
                    break;

                case 3:
                    System.out.print("Enter Student ID to Update: ");
                    int usid = sc.nextInt(); sc.nextLine();
                    System.out.print("New First Name: ");
                    String ufname = sc.nextLine();
                    System.out.print("New Last Name: ");
                    String ulname = sc.nextLine();
                    System.out.print("New DOB: ");
                    String udob = sc.nextLine();
                    System.out.print("New Email: ");
                    String uemail = sc.nextLine();
                    System.out.print("New Phone: ");
                    String uphone = sc.nextLine();

                    Student updatedStudent = new Student(usid, ufname, ulname, udob, uemail, uphone);
                    studentDAO.updateStudent(updatedStudent);
                    break;

                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int dsid = sc.nextInt();
                    studentDAO.deleteStudent(dsid);
                    break;

                case 5:
                    System.out.print("Enter Course ID: ");
                    int cid = sc.nextInt(); sc.nextLine();
                    System.out.print("Course Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Course Code: ");
                    String ccode = sc.nextLine();
                    System.out.print("Instructor Name: ");
                    String iname = sc.nextLine();
                    Course c = new Course(cid, cname, ccode, iname);
                    courseDAO.addCourse(c);
                    break;

                case 6:
                    System.out.print("Enter Course ID to View: ");
                    int vcid = sc.nextInt();
                    courseDAO.displayCourseDetails(vcid);
                    break;

                case 7:
                    System.out.print("Enter Teacher ID: ");
                    int tid = sc.nextInt(); sc.nextLine();
                    System.out.print("First Name: ");
                    String tfn = sc.nextLine();
                    System.out.print("Last Name: ");
                    String tln = sc.nextLine();
                    System.out.print("Email: ");
                    String temail = sc.nextLine();
                    System.out.print("Expertise: ");
                    String texp = sc.nextLine();
                    Teacher t = new Teacher(tid, tfn, tln, temail, texp);
                    teacherDAO.addTeacher(t);
                    break;

                case 8:
                    System.out.print("Enter Teacher ID to View: ");
                    int vtid = sc.nextInt();
                    teacherDAO.displayTeacherDetails(vtid);
                    break;

                case 9:
                    System.out.print("Enter Enrollment ID: ");
                    int eid = sc.nextInt();
                    System.out.print("Enter Student ID: ");
                    int stdId = sc.nextInt();
                    System.out.print("Enter Course ID: ");
                    int crsId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enrollment Date (yyyy-mm-dd): ");
                    String edate = sc.nextLine();
                    Enrollment e = new Enrollment(eid, stdId, crsId, edate, null, null);
                    enrollmentDAO.enrollStudent(e);
                    break;
                    
                case 10:
                    System.out.print("Enter Course Code: ");
                    String assignCode = sc.nextLine();

                    System.out.print("Enter Teacher ID: ");
                    int assignTid = sc.nextInt(); sc.nextLine();

                    Course assignedCourse = courseDAO.getCourseByCode(assignCode);
                    Teacher assignedTeacher = teacherDAO.getTeacherById(assignTid);

                    if (assignedCourse != null && assignedTeacher != null) {
                        assignedCourse.assignTeacher(assignedTeacher); // Link teacher
                        courseDAO.updateCourse(assignedCourse); // Update DB
                        System.out.println("✅ Teacher assigned to course successfully.");
                    } else {
                        System.out.println("❌ Course or Teacher not found.");
                    }
                    break;


                case 11:
                    System.out.print("Enter Enrollment ID to View: ");
                    int evid = sc.nextInt();
                    enrollmentDAO.displayEnrollmentDetails(evid);
                    break;

                case 12:
                    System.out.print("Enter Payment ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Enter Student ID: ");
                    int psid = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    double amt = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Payment Date (yyyy-mm-dd): ");
                    String pdate = sc.nextLine();
                    Payment p = new Payment(pid, psid, amt, pdate);
                    paymentDAO.makePayment(p);
                    break;

                case 13:
                    System.out.print("Enter Payment ID to View: ");
                    int vpid = sc.nextInt();
                    paymentDAO.displayPaymentDetails(vpid);
                    break;
                
                case 0:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
