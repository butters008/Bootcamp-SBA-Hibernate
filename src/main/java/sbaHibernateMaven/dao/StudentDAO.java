package sbaHibernateMaven.dao;


import sbaHibernateMaven.entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents();
    Student getStudentByEmail(String email);
    boolean validateStudent(String email, String password);
//    void registerStudentToCourse(String studentEmail, String cId);
//    List<Course> getStudentCourse(String email);
}
