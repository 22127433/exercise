package com.example.java.exercises.task2;

public class Developer extends Employee {
    private String programmingLanguage;

    public Developer(int id, String name, double salary, String programmingLanguage) {
        super(id, name, salary);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    @Override
    public double calculateBonus(){
        return super.calculateBonus() * 0.2f;
    }
}
