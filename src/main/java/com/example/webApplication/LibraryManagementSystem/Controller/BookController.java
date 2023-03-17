package com.example.webApplication.LibraryManagementSystem.Controller;

import com.example.webApplication.LibraryManagementSystem.DTO.BookRequestDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.BookResponseDTO;
import com.example.webApplication.LibraryManagementSystem.Entity.Book;
import com.example.webApplication.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public BookResponseDTO addBook(@RequestBody BookRequestDTO bookRequestDTO) throws Exception{
        return bookService.addBook(bookRequestDTO);
    }

}
