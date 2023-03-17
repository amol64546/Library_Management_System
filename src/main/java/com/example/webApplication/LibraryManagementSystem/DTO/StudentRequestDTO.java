package com.example.webApplication.LibraryManagementSystem.DTO;


import com.example.webApplication.LibraryManagementSystem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDTO {

    private String name;
    private String email;
    private int age;
    private Department department;
}
