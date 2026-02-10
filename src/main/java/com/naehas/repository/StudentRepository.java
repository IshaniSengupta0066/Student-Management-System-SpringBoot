package com.naehas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naehas.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Spring Data JPA automatically provides:
    // - save(Student) - to save/update
    // - findById(Integer) - to find by ID
    // - findAll() - to get all students
    // - deleteById(Integer) - to delete
    // No need to write any code!
}