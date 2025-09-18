package com.example.java.exercises.task8;

import java.lang.reflect.*;

public class Main {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PHONE_REGEX = "^\\+?[0-9]{9,15}$";


    public static void validate(Object object) throws NoSuchFieldException {
        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                String name = field.getName();
                switch (value) {
                    case String s when s.length() > 50 ->
                        System.out.println(name + ": " + s + " exceeds the maximum length 50");
                    case String s when s.matches(EMAIL_REGEX) ->
                        System.out.println(name + ": " + s + " matches email");
                    case String s when s.matches(PHONE_REGEX) ->
                        System.out.println(name + ": " + s + " matches phone number");
                    case String s when s.isEmpty() ->
                        System.out.println(name + ": is empty");
                    case String s ->
                        System.out.println(name + ": " + s + " is a normal string");
                    case Double d when (Double.isInfinite(d) || Double.isNaN(d)) ->
                        System.out.println(name + ": " + d + " is infinite or NaN");
                    case Double d when d < 0 ->
                        System.out.println(name + ": " + d + " is negative");
                    case Double d ->
                        System.out.println(name + ": " + d + " is a normal double");
                    case Integer i ->
                        System.out.println(name + ": " + i + " is a normal integer");
                    default ->
                        System.out.println(name + ": " + value);
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

//    static void validate(Object object) throws NoSuchFieldException {
//        Class<?> clazz = object.getClass();
//
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            String name =  field.getName();
//            Class<?> type = field.getType();
//            try {
//                Object value = field.get(object);
//                String valueToString = value.toString();
//                if (type.equals(String.class)) {
//                    if (valueToString.matches(EMAIL_REGEX)) {
//                        System.out.println(name + ": " + valueToString + " is email");
//                    }
//                    else if (valueToString.matches(PHONE_REGEX)) {
//                        System.out.println(name + ": " + valueToString + " is phone number");
//                    }
//                    else System.out.println(name + ": " + valueToString + " is a normal string");
//                }
//                else if (type.equals(int.class) || type.equals(Integer.class)) {
//                    int intNumber = (int) value;
//                    if (intNumber % 2 == 0){
//                        System.out.println(name + ": " + valueToString + " is an even integer");
//                    }
//                    else System.out.println(name + ": " + valueToString + " is an odd integer");
//                }
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    public static void main(String[] args) {
        TestClass object = new TestClass(1, 2, -212.232323, "");
        try {
            validate(object);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}