package com.example.webApplication.LibraryManagementSystem.Service;

import com.example.webApplication.LibraryManagementSystem.DTO.AuthorRequestDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.AuthorResponseDTO;
import com.example.webApplication.LibraryManagementSystem.Entity.Author;
import com.example.webApplication.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public void addAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = new Author();
        author.setName(authorRequestDTO.getName());
        author.setAge(authorRequestDTO.getAge());
        author.setEmail(authorRequestDTO.getEmail());
        author.setMobNo(authorRequestDTO.getMobNo());

        authorRepository.save(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
