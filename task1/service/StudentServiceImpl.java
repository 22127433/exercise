package com.example.java.exercises.task1.service;

import com.example.java.exercises.task1.dto.StudentModifyDTO;
import com.example.java.exercises.task1.dto.StudentResponseDTO;
import com.example.java.exercises.task1.mapper.StudentMapper;
import com.example.java.exercises.task1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,  StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentResponseDTO> getStudentList(){
       return studentMapper.toResponseDTOs(studentRepository.getStudentList());
    }

    @Override
    public void addStudent(StudentModifyDTO student) {
        studentRepository.addStudent(studentMapper.toEntity(student));
    }

    @Override
    public StudentResponseDTO getStudent(int id) {
        return studentMapper.toResponseDTO(studentRepository.getStudent(id));
    }

    @Override
    public void deleteStudent(int id) {
        if (studentRepository.getStudent(id) != null) {
            studentRepository.deleteStudent(id);
        }
    }

    @Override
    public void updateStudent(int id, StudentModifyDTO student) {
        if (studentRepository.getStudent(id) != null) {
            studentRepository.updateStudent(studentMapper.toEntity(student, id));
        }
    }
}
