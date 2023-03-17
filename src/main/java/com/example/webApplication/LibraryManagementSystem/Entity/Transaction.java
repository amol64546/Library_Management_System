package com.example.webApplication.LibraryManagementSystem.Entity;

import com.example.webApplication.LibraryManagementSystem.Enum.Transaction_Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.transaction.TransactionStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String transactionNo;

    @Enumerated(EnumType.STRING)
    private Transaction_Status transactionStatus;

    @CreationTimestamp
    private Date transactionDate;

    private boolean isIssuedOperation;

    private String message;

    @ManyToOne
    @JoinColumn
    Book book;

    @ManyToOne
    @JoinColumn
    Card card;


}
