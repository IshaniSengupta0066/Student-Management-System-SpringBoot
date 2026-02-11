package com.naehas.service;

import com.naehas.model.Student;
import com.naehas.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudentById(int id) {
        logger.debug("Fetching student with ID: {}", id);
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student saveStudent(Student student) {
        if (student == null) {
            logger.error("Attempted to save null student");
            throw new IllegalArgumentException("Student cannot be null");
        }
        logger.debug("Saving student: {}", student.getName());
        Student savedStudent = studentRepository.save(student);
        logger.info("Student saved with ID: {}", savedStudent.getRollNo());
        return savedStudent;
    }

    public Page<Student> getAllStudents(int page, int size) {
        logger.debug("Fetching students - Page: {}, Size: {}", page, size);
        
        // Create Pageable object with page number and size
        Pageable pageable = PageRequest.of(page, size);
        
        // Get paginated results
        Page<Student> students = studentRepository.findAll(pageable);
        
        logger.info("Retrieved {} students from page {} of {}", 
            students.getNumberOfElements(), page, students.getTotalPages());
        return students;
    }

    public Student updateStudent(int id, Student student) {
        logger.debug("Updating student with ID: {}", id);
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            Student studentToUpdate = existingStudent.get();
            studentToUpdate.setName(student.getName());
            studentToUpdate.setAge(student.getAge());
            Student updated = studentRepository.save(studentToUpdate);
            logger.info("Student with ID: {} updated successfully", id);
            return updated;
        }
        logger.warn("Student with ID: {} not found for update", id);
        return null;
    }

    public boolean deleteStudent(int id) {
        logger.debug("Attempting to delete student with ID: {}", id);
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            logger.info("Student with ID: {} deleted successfully", id);
            return true;
        }
        logger.warn("Student with ID: {} not found for deletion", id);
        return false;
    }
}
