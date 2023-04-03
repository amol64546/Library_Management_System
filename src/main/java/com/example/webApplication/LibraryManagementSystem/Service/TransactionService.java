package com.example.webApplication.LibraryManagementSystem.Service;

import com.example.webApplication.LibraryManagementSystem.DTO.IssueBookRequestDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.IssueBookResponseDto;
import com.example.webApplication.LibraryManagementSystem.Entity.Book;
import com.example.webApplication.LibraryManagementSystem.Entity.Card;
import com.example.webApplication.LibraryManagementSystem.Entity.Transaction;
import com.example.webApplication.LibraryManagementSystem.Enum.CardStatus;
import com.example.webApplication.LibraryManagementSystem.Enum.Transaction_Status;
import com.example.webApplication.LibraryManagementSystem.CustomExceptions.InvalidBookException;
import com.example.webApplication.LibraryManagementSystem.CustomExceptions.InvalidCardException;
import com.example.webApplication.LibraryManagementSystem.Repository.BookRepository;
import com.example.webApplication.LibraryManagementSystem.Repository.CardRepository;
import com.example.webApplication.LibraryManagementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private JavaMailSender emailSender;


    public IssueBookResponseDto issueBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception{

        Transaction transaction = new Transaction();
        transaction.setTransactionNo(String.valueOf(UUID.randomUUID()));
        transaction.setIssuedOperation(true);

        // check for card and book
        Card card;
        try{
            // NoSuchElementException
             card = cardRepository.findById(issueBookRequestDTO.getCardId()).get();
        }catch(Exception e){
             transaction.setTransactionStatus(Transaction_Status.FAILED);
             transaction.setMessage("Invalid card ID");
             transactionRepository.save(transaction);
             throw new InvalidCardException("Invalid card ID");
        }

        Book book;
        try{
            // NoSuchElementException
             book = bookRepository.findById(issueBookRequestDTO.getBookId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(Transaction_Status.FAILED);
            transaction.setMessage("Invalid book ID");
            transactionRepository.save(transaction);
            throw new InvalidBookException("Invalid book ID");
        }

        transaction.setBook(book);
        transaction.setCard(card);

        // check for issue status of book
        if(card.getCardStatus() != CardStatus.ACTIVATED )  {
            transaction.setTransactionStatus(Transaction_Status.FAILED);
            transaction.setMessage("Your card is not activated.");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not activated.");
        }
        if(book.isIssued()){
            transaction.setTransactionStatus(Transaction_Status.FAILED);
            transaction.setMessage("Sorry! Book is already issued.");
            transactionRepository.save(transaction);
            throw new Exception("Sorry! Book is already issued.");
        }

        // Issue book
        transaction.setTransactionStatus(Transaction_Status.SUCCESS);
        transaction.setMessage("Transaction was successful");

        book.setIssued(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);

        card.getTransactionList().add(transaction);
        card.getBooksIssued().add(book);

        cardRepository.save(card);

        // response dto
        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setTransactionId(transaction.getTransactionNo());
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransactionStatus(Transaction_Status.SUCCESS);

//        // sending email
//        String text = "Congrats!! "+card.getStudent().getName()+" You have been issued " + book.getTitle() +" book";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("amolnakhate240@gmail.com");
//        message.setTo(card.getStudent().getEmail());
//        message.setSubject("Issue book notification");
//        message.setText(text);
//        emailSender.send(message);


        return issueBookResponseDto;
    }

    public String getAllTxns(int cardId){
        List<Transaction> transactionList = transactionRepository.getAllSuccessfullTxnsWithCardNo(cardId);
        String ans = "";
        for(Transaction t: transactionList){
            ans += t.getTransactionNo();
            ans += "\n";
        }

        return ans;
    }

    public IssueBookResponseDto returnBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNo(String.valueOf(UUID.randomUUID()));
        transaction.setIssuedOperation(false);

        // check for card and book
        Card card;
        try{
            // NoSuchElementException
            card = cardRepository.findById(issueBookRequestDTO.getCardId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(Transaction_Status.FAILED);
            transaction.setMessage("Invalid card ID");
            transactionRepository.save(transaction);
            throw new InvalidCardException("Invalid card ID");
        }

        Book book;
        try{
            // NoSuchElementException
             book = bookRepository.findById(issueBookRequestDTO.getBookId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(Transaction_Status.FAILED);
            transaction.setMessage("Invalid book ID");
            transactionRepository.save(transaction);
            throw new InvalidBookException("Invalid book ID");
        }

        if(!card.getBooksIssued().contains(book)){
            throw new Exception("This book is not issued");
        }


        transaction.setBook(book);
        transaction.setCard(card);



        // return book
        transaction.setTransactionStatus(Transaction_Status.SUCCESS);
        transaction.setMessage("Transaction was successful");

        book.setIssued(false);
        book.setCard(card);
        book.getTransactionList().add(transaction);

        card.getTransactionList().add(transaction);
        card.getBooksIssued().remove(book);

        cardRepository.save(card);

        // response dto
        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setTransactionId(transaction.getTransactionNo());
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransactionStatus(Transaction_Status.SUCCESS);

//        // sending email
//        String text = "Congrats!! "+card.getStudent().getName()+" You have been issued " + book.getTitle() +" book";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("amolnakhate240@gmail.com");
//        message.setTo(card.getStudent().getEmail());
//        message.setSubject("Issue book notification");
//        message.setText(text);
//        emailSender.send(message);


        return issueBookResponseDto;


    }
}
