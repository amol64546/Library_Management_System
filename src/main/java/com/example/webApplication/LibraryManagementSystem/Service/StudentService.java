package com.example.webApplication.LibraryManagementSystem.Service;

import com.example.webApplication.LibraryManagementSystem.DTO.StudentRequestDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.StudentResponseDTO;
import com.example.webApplication.LibraryManagementSystem.DTO.StudentUpdateEmailRequestDTO;
import com.example.webApplication.LibraryManagementSystem.Entity.Card;
import com.example.webApplication.LibraryManagementSystem.Entity.Student;
import com.example.webApplication.LibraryManagementSystem.Enum.CardStatus;
import com.example.webApplication.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public String addStudent(StudentRequestDTO studentRequestDTO) {

        // create student obj
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setDepartment(studentRequestDTO.getDepartment());
        student.setEmail(studentRequestDTO.getEmail());


        // create card object
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        student.setCard(card);

        studentRepository.save(student);
        return "Student has been added";
    }

    public String findStudentByEmail(String email) {
        return studentRepository.findByEmail(email).getName();
    }

    public StudentResponseDTO updateEmail(StudentUpdateEmailRequestDTO studentUpdateEmailRequestDTO) {
        Student student = studentRepository.findById(studentUpdateEmailRequestDTO.getId()).get();
        student.setEmail(studentUpdateEmailRequestDTO.getEmail());

        Student updatedStudent = studentRepository.save(student);

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(updatedStudent.getId());
        studentResponseDTO.setEmail(updatedStudent.getEmail());
        studentResponseDTO.setName(updatedStudent.getName());

        return studentResponseDTO;

    }
}
