package com.example.webApplication.LibraryManagementSystem.Controller;

import com.example.webApplication.LibraryManagementSystem.DTO.AuthorRequestDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.AuthorResponseDTO;
import com.example.webApplication.LibraryManagementSystem.Entity.Author;
import com.example.webApplication.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {


    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorRequestDTO authorRequestDTO){
         authorService.addAuthor(authorRequestDTO);
         return "Author has been added";
    }

    @GetMapping("/get_authors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }



}
