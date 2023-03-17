package com.example.webApplication.LibraryManagementSystem.DTO;

import com.example.webApplication.LibraryManagementSystem.Enum.Transaction_Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueBookResponseDto {
    private String transactionId;
    private String bookName;
    private Transaction_Status transactionStatus;
}
