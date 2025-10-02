package com.example.java.exercises.task1.mapper;

import com.example.java.exercises.task1.dto.StudentModifyDTO;
import com.example.java.exercises.task1.dto.StudentResponseDTO;
import com.example.java.exercises.task1.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

        return students
                .stream()
                .map(student -> new StudentResponseDTO(
                        student.getId(),
                        student.getName(),
                        student.getAge(),
                        student.getGpa()))
                .collect(Collectors.toList());
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
