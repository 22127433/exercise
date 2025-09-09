package com.example.java.exercises.task3;

public class PaypalPayment extends Payment {
    public void processPayment(double amount) {
        System.out.println("Paypal Payment: " + amount);
    }
}
