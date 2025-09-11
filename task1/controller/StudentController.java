package com.example.java.exercises.task1.controller;

import com.example.java.exercises.task1.dto.StudentModifyDTO;
import com.example.java.exercises.task1.dto.StudentResponseDTO;
import com.example.java.exercises.task1.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/student/list")
    public List<StudentResponseDTO> getStudentList(){
        return studentService.getStudentList();
    }

    @GetMapping(value = "/student/{id}")
    public StudentResponseDTO getStudent(@PathVariable int id){
        return studentService.getStudent(id);
    }

    @PostMapping(value = "/student/add")
    public void addStudent(@RequestBody StudentModifyDTO student){
        studentService.addStudent(student);
    }

    @DeleteMapping(value = "/student/delete/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }

    @PutMapping(value = "/student/update/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody StudentModifyDTO student){
        studentService.updateStudent(id, student);
    }
}
