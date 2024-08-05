/*
package com.trial.demotrial.StudentService;
import com.trial.demotrial.student.Student;
import com.trial.demotrial.Interface.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

  public List<Student> getStudents(){
        return List.of(
                new Student(1L, "Mariam", "Jamal@gmail.com", LocalDate.of(2000, Month.JANUARY,5), 21),
                new Student(2L, "John", "john@example.com", LocalDate.of(1998, Month.MAY, 15), 26),
                new Student(3L, "Sarah", "sarah@example.com", LocalDate.of(2001, Month.SEPTEMBER, 20), 23),
                new Student(4L, "David", "david@example.com", LocalDate.of(1999, Month.MARCH, 10), 22)
        );
    }
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public boolean deleteStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}*/
 /*public List<Student> getStudents() {
        return studentRepository.findAll();
    }*/

package com.trial.demotrial.StudentService;

import com.trial.demotrial.Interface.StudentInterface;
import com.trial.demotrial.student.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements StudentInterface {
    private List<Student> students = new ArrayList<>();

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Student getStudentById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Student saveStudent(Student student) {
        // If the student ID is null, it's a new student, so add it to the list
        if (student.getId() == null) {
            student.setId((long) (students.size() + 1));
            students.add(student);
        } else {
            // Update existing student
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId().equals(student.getId())) {
                    students.set(i, student);
                    break;
                }
            }
        }
        return student;
    }

    @Override
    public List<Student> saveStudents(List<Student> students) {
        List<Student> savedStudents = new ArrayList<>();
        for (Student student : students) {
            savedStudents.add(saveStudent(student));
        }
        return savedStudents;
    }

    @Override
    public boolean deleteStudent(Long id) {
        Student student = getStudentById(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;
    }
}