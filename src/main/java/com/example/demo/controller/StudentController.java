package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

   @Autowired
   private StudentService studentService;

   @GetMapping
   public List<Student> getAllStudents() {
       return studentService.getAllStudents();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
       Optional<Student> student = studentService.getStudentById(id);
       return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   }

   @PostMapping
   public Student addStudent(@RequestBody Student student) {
       return studentService.saveStudent(student);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
       Optional<Student> student = studentService.getStudentById(id);
       if (student.isPresent()) {
           Student existingStudent = student.get();
           existingStudent.setName(updatedStudent.getName());
           existingStudent.setEmail(updatedStudent.getEmail());
           existingStudent.setGrade(updatedStudent.getGrade());
           studentService.saveStudent(existingStudent);
           return ResponseEntity.ok(existingStudent);
       } else {
           return ResponseEntity.notFound().build();
       }
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
       if (studentService.getStudentById(id).isPresent()) {
           studentService.deleteStudent(id);
           return ResponseEntity.noContent().build();
       } else {
           return ResponseEntity.notFound().build();
       }
   }
}
