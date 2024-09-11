package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
 
@Data
@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
 
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
 
    private String grade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	/*public Enrollment(Long id, Student student, Course course, String grade) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.grade = grade;
	}

	public Enrollment() {
		super();
	}*/
}