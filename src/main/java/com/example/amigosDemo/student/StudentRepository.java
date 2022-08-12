package com.example.amigosDemo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
Student will be our "T"/Object type.
Long will be our "ID" type
 */

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {

    // SELECT * from student WHERE email = ?
    // This is JPQL, NOT Structured Query Language (SQL)

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}
