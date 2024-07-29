package com.trial.demotrial.StudentService;

import com.trial.demotrial.student.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
   /* public List<Student> getStudents(){
        return List.of(
                new Student(
                        1L,
                        "Mariam",
                        "Jamal@gmail.com",
                        LocalDate.of(2000, Month.JANUARY,5),
                        21
                )
        );
    }*/
   private List<Student> students = new ArrayList<>(List.of(
           new Student(1L, "Mariam", "Jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 5), 21)
   ));

    public List<Student> getStudents() {
        return students;
    }
    public Optional<Student> getStudentById(Long id) {
        return students.stream().filter(student -> student.getId().equals(id)).findFirst();
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, updatedStudent);
                return updatedStudent;
            }
        }
        return null; // or throw an exception
    }

    public boolean deleteStudent(Long id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
}
