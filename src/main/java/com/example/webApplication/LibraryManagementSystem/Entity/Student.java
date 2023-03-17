package com.example.webApplication.LibraryManagementSystem.Entity;

import com.example.webApplication.LibraryManagementSystem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    private int age;

    @Enumerated(EnumType.STRING)
    private Department department;


    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    Card card;
}
