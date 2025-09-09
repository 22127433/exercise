package com.example.java.exercises.task5;

import java.util.ArrayList;
import java.util.List;

public class Order implements Discountable{
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public double applyDiscount() {
        return 0.02f;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public double calculateOrder(){
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.calculatePrice();
        }

        return total * applyDiscount();
    }
}
