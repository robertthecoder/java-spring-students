package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> // Long is for the student id , JPA IS A BOILERPLATE FOR SQL QUERIES
{

    //    Jdql not psql
    // Since we used @Entity on Student.java class, we can select from "Student"
    @Query("SELECT s FROM Student s WHERE s.email= ?1")
    Optional<Student> findStudentByEmail(String email);

}
