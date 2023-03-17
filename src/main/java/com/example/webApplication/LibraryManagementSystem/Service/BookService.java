package com.example.webApplication.LibraryManagementSystem.Service;

import com.example.webApplication.LibraryManagementSystem.DTO.BookRequestDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.BookResponseDTO;
import com.example.webApplication.LibraryManagementSystem.Entity.Author;
import com.example.webApplication.LibraryManagementSystem.Entity.Book;
import com.example.webApplication.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.webApplication.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) throws Exception{

        // get author object by authorId
        Author author;
        try{
             author = authorRepository.findById(bookRequestDTO.getAuthorId()).get();
        }catch(Exception e){
            throw new Exception("Author is not present");
        }

        // creating book obj
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setGenre(bookRequestDTO.getGenre());
        book.setPrice(bookRequestDTO.getPrice());
        book.setIssued(false);
        book.setAuthor(author);

        author.getBooks().add(book);
        authorRepository.save(author);

        // response dto
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setPrice(book.getPrice());

        return bookResponseDTO;

    }
}
