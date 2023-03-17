package com.example.webApplication.LibraryManagementSystem.Controller;

import com.example.webApplication.LibraryManagementSystem.DTO.StudentRequestDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.StudentResponseDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.StudentUpdateEmailRequestDTO;
import com.example.webApplication.LibraryManagementSystem.Entity.Student;
import com.example.webApplication.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDTO studentRequestDTO){
        return studentService.addStudent(studentRequestDTO);
    }

    @GetMapping("/find_by_email")
    public String findStudentByEmail(@RequestParam("email") String email){
        return studentService.findStudentByEmail(email);
    }

    @PutMapping("/update_email")
    public StudentResponseDTO updateEmail(@RequestBody StudentUpdateEmailRequestDTO studentUpdateEmailRequestDTO){
        return studentService.updateEmail(studentUpdateEmailRequestDTO);
    }




}
