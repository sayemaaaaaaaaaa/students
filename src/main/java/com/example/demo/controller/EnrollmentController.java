package com.example.demo.controller;


import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Student;
import com.example.demo.entity.Course;
import com.example.demo.service.EnrollmentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

   @Autowired
   private EnrollmentService enrollmentService;

   @Autowired
   private StudentService studentService;

   @Autowired
   private CourseService courseService;

   @GetMapping
   public List<Enrollment> getAllEnrollments() {
       return enrollmentService.getAllEnrollments();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
       Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(id);
       return enrollment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<Enrollment> addEnrollment(@RequestParam Long studentId, @RequestParam Long courseId,@RequestParam String grade) {
       Optional<Student> student = studentService.getStudentById(studentId);
       Optional<Course> course = courseService.getCourseById(courseId);
       if (student.isPresent() && course.isPresent()) {
           Enrollment enrollment = new Enrollment();
           enrollment.setStudent(student.get());
           enrollment.setCourse(course.get());
           enrollment.setGrade(grade);
           enrollmentService.saveEnrollment(enrollment);
           return ResponseEntity.ok(enrollment);
       } else {
           return ResponseEntity.badRequest().build();
       }
   }
// Update enrollment
   @PutMapping("/{id}")
   public ResponseEntity<Enrollment> updateEnrollment(
           @PathVariable Long id,
           @RequestParam Long studentId,
           @RequestParam Long courseId,
           @RequestParam String grade) {
       
       Optional<Student> student = studentService.getStudentById(studentId);
       Optional<Course> course = courseService.getCourseById(courseId);
       
       if (student.isPresent() && course.isPresent()) {
           Optional<Enrollment> existingEnrollment = enrollmentService.getEnrollmentById(id);
           if (existingEnrollment.isPresent()) {
               Enrollment enrollment = existingEnrollment.get();
               enrollment.setStudent(student.get());
               enrollment.setCourse(course.get());
               enrollment.setGrade(grade); // Update the grade
               enrollmentService.saveEnrollment(enrollment);
               return ResponseEntity.ok(enrollment);
           } else {
               return ResponseEntity.notFound().build();
           }
       } else {
           return ResponseEntity.badRequest().build();
       }
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
       if (enrollmentService.getEnrollmentById(id).isPresent()) {
           enrollmentService.deleteEnrollment(id);
           return ResponseEntity.noContent().build();
       } else {
           return ResponseEntity.notFound().build();
       }
   }
}

