package com.example.demo.service;

import com.example.demo.entity.Enrollment;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class EnrollmentService {
 
    @Autowired
    private EnrollmentRepository enrollmentRepository;
 
    public List<Enrollment> getAllEnrollments() {
        return (List<Enrollment>) enrollmentRepository.findAll();
    }
 
    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }
    
 
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }
 
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}