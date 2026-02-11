package com.naehas.controller;

import com.naehas.model.Student;
import com.naehas.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        logger.info("GET /api/students - Page: {}, Size: {}", page, size);
        Page<Student> students = studentService.getAllStudents(page, size);
        logger.info("GET /api/students - Found {} students", students.getTotalElements());
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        logger.info("GET /api/students/{} - Fetching student", id);
        Student student = studentService.getStudentById(id);
        if (student == null) {
            logger.warn("GET /api/students/{} - Student not found", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("GET /api/students/{} - Student found", id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        logger.info("POST /api/students - Creating student: {}", student.getName());
        Student savedStudent = studentService.saveStudent(student);
        logger.info("POST /api/students - Student created with ID: {}", savedStudent.getRollNo());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping("/{id}")
    // id is the path variable , extracts id from URL.
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        logger.info("PUT /api/students/{} - Updating student", id);
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent == null) {
            logger.warn("PUT /api/students/{} - Student not found", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("PUT /api/students/{} - Student updated successfully", id);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        logger.info("DELETE /api/students/{} - Deleting student", id);
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            logger.info("DELETE /api/students/{} - Student deleted successfully", id);
            return ResponseEntity.ok("Student with ID " + id + " deleted successfully");
        }
        logger.warn("DELETE /api/students/{} - Student not found", id);
        return ResponseEntity.notFound().build();
    }
}
