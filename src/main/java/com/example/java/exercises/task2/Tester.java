package com.example.java.exercises.task2;

public class Tester extends Employee {
    private String testTool;

    public Tester(int id, String name, double salary, String testTool){
        super(id, name, salary);
        this.testTool = testTool;
    }

    public String getTestTool() {
        return testTool;
    }

    @Override
    public double calculateBonus(){
        return super.calculateBonus() * 0.15f;
    }
}
