package com.example.webApplication.LibraryManagementSystem.Repository;

import com.example.webApplication.LibraryManagementSystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    // findBy{attribute name}
    Student findByEmail(String email);

    List<Student> findByAge(int age);
}
