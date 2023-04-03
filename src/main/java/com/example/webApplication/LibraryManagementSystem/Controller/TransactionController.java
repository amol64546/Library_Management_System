package com.example.webApplication.LibraryManagementSystem.Controller;

import com.example.webApplication.LibraryManagementSystem.DTO.IssueBookRequestDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.IssueBookResponseDto;
import com.example.webApplication.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO) throws Exception{
        IssueBookResponseDto issueBookResponseDto;
        try{
            issueBookResponseDto = transactionService.issueBook(issueBookRequestDTO);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(issueBookResponseDto, HttpStatus.ACCEPTED);
    }

    @PostMapping("/return")
    public ResponseEntity returnBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO) throws Exception{
        IssueBookResponseDto issueBookResponseDto;
        try{
            issueBookResponseDto = transactionService.returnBook(issueBookRequestDTO);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(issueBookResponseDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/get")
    public String getAllTxns(@RequestParam("cardId") int cardId){
        return transactionService.getAllTxns(cardId);
    }
}
