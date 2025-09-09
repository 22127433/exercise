package com.example.java.exercises.task1;

import java.util.List;

public class StudentManager {
    private final List<Student> studentList;

    public StudentManager(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    // Add student
    public void addStudent(Student student) {
        studentList.add(student);
    }
    public void addStudent(int id, String name, Integer age, Integer gpa) {
        Student st = new Student(id, name, age, gpa);
        studentList.add(st);
    }

    // Delete student
    public boolean deleteStudent(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                studentList.remove(student);
                return true;
            }
        }
        return false;
    }
    public boolean deleteStudent(String name) {
        for (Student student : studentList) {
            if (student.getName().equals(name)) {
                studentList.remove(student);
                return true;
            }
        }
        return false;
    }

    // Search student
    public Student searchStudentById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }
}