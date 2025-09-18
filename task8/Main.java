package com.example.java.exercises.task8;

import java.lang.reflect.*;
import java.util.Scanner;

public class Main {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PHONE_REGEX = "^\\+?[0-9]{9,15}$";

    static void validate(Object object) throws NoSuchFieldException {
        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name =  field.getName();
            Class<?> type = field.getType();
            try {
                Object value = field.get(object);
                String valueToString = value.toString();
                if (type.equals(String.class)) {
                    if (valueToString.matches(EMAIL_REGEX)) {
                        System.out.println(name + ": " + valueToString + " is email");
                    }
                    else if (valueToString.matches(PHONE_REGEX)) {
                        System.out.println(name + ": " + valueToString + " is phone number");
                    }
                    else System.out.println(name + ": " + valueToString + " is a normal string");
                }
                else if (type.equals(int.class) || type.equals(Integer.class)) {
                    int number = (int) value;
                    if (number % 2 == 0){
                        System.out.println(name + ": " + valueToString + " is an even number");
                    }
                    else System.out.println(name + ": " + valueToString + " is an odd number");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String text = scanner.nextLine();
//        int number = scanner.nextInt();
//        TestClass object = new TestClass(number, text);
        TestClass object = new TestClass(15, "nnatu22@clc.fitus.edu.vn");
        try {
            validate(object);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}