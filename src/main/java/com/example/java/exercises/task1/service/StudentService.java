package com.example.java.exercises.task1.service;

import com.example.java.exercises.task1.dto.StudentModifyDTO;
import com.example.java.exercises.task1.dto.StudentResponseDTO;

import java.util.List;


public interface StudentService {
    List<StudentResponseDTO> getStudentList();
    StudentResponseDTO getStudent(int id);
    void addStudent(StudentModifyDTO student);
    void deleteStudent(int id);
    void updateStudent(int id, StudentModifyDTO student);
}
