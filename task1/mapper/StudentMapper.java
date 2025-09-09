package com.example.java.exercises.task1.mapper;

import com.example.java.exercises.task1.dto.StudentModifyDTO;
import com.example.java.exercises.task1.dto.StudentResponseDTO;
import com.example.java.exercises.task1.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {
    public StudentResponseDTO toResponseDTO(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getGpa()
        );
    }

    public List<StudentResponseDTO> toResponseDTOs(List<Student> students) {
        if (students == null) {
            return null;
        }

        List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
        for (Student student : students) {
            studentResponseDTOList.add(toResponseDTO(student));
        }

        return studentResponseDTOList;
    }

    public Student toEntity(StudentModifyDTO dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setName(dto.name());
        student.setAge(dto.age());
        student.setGpa(dto.gpa());
        return student;
    }

    public Student toEntity(StudentModifyDTO dto, int id) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        student.setName(dto.name());
        student.setAge(dto.age());
        student.setGpa(dto.gpa());
        return student;
    }
}
