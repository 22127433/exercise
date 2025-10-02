package com.example.java.exercises.task3;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Payment pm = new CreditCardPayment();
            payments.add(pm);
        }
        for (int i = 1; i < 5; i++) {
            Payment pm = new PaypalPayment();
            payments.add(pm);
        }

        // Test
        for (Payment p : payments) {
            if (p instanceof CreditCardPayment) {
                p.processPayment(10);
            }
            else if (p instanceof PaypalPayment) {
                p.processPayment(20);
            }
        }
    }
}