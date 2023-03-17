package com.example.webApplication.LibraryManagementSystem.Repository;

import com.example.webApplication.LibraryManagementSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
