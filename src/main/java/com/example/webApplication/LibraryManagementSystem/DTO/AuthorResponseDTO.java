package com.example.webApplication.LibraryManagementSystem.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorResponseDTO {
    private String name;

    private int age;

    private String mobNo;

    private String email;
}
