package com.example.java.exercises.task1.service;

import com.example.java.exercises.task1.dto.StudentModifyDTO;
import com.example.java.exercises.task1.dto.StudentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    public List<StudentResponseDTO> getStudentList();
    public StudentResponseDTO getStudent(int id);
    public void addStudent(StudentModifyDTO student);
    public void deleteStudent(int id);
    public void updateStudent(int id, StudentModifyDTO student);
}
