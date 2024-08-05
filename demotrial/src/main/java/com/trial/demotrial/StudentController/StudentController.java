package com.trial.demotrial.StudentController;

import com.trial.demotrial.student.Student;
import com.trial.demotrial.StudentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/student")

public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentservice) {
        this.studentService = studentservice;
    }
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }
  @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent == null) {
            return ResponseEntity.notFound().build();
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());

        Student savedStudent = studentService.saveStudent(existingStudent);

        return ResponseEntity.ok(savedStudent);
    }
   /*@PutMapping("/{id}")
   public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
       student.setId(id); // Ensure ID consistency
       Student updatedStudent = studentService.saveStudent(student);

       if (updatedStudent != null) {
           return ResponseEntity.ok(updatedStudent);
       } else {
           return ResponseEntity.notFound().build();
       }
   }*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<List<Student>>  createStudent(@RequestBody List<Student> student) {
        List<Student> savedStudent = studentService.saveStudents(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
}
