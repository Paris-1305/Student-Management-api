package com.trial.demotrial.Interface;

import com.trial.demotrial.student.Student;

import java.util.List;
public interface StudentInterface {
    List<Student> getStudents();
    Student getStudentById(Long id);
    Student saveStudent(Student student);
    boolean deleteStudent(Long id);
    List<Student> saveStudents(List<Student> students);
    List<Student> addStudents(List<Student> students);
}
