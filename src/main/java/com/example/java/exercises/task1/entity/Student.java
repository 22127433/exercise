package com.example.java.exercises.task1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int age;
    private float gpa;

    // Constructor
    public Student(){
        id = 0;
        name = "default name";
        age = 21;
        gpa = 4.0f;
    }
    public Student (int id, String name, int age, float gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    // Getter
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public float getGpa() {
        return gpa;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        if (age < 0) age = 0;
        this.age = age;
    }
    public void setGpa(float gpa) {
        if (gpa < 0) gpa = 0;
        else if (gpa > 4) gpa = 4;
        this.gpa = gpa;
    }

    public String toString() {
        return id + ' ' + name + ' ' + age + ' ' + gpa;
    }
}
