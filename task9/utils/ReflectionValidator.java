package com.example.java.exercises.task9.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Component
public class ReflectionValidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PHONE_REGEX = "^\\+?[0-9]{9,15}$";

    private static boolean isNullableField(String fieldName, String className) {
        return switch (className) {
            case "Customers" -> List.of("email", "phone", "updatedAt").contains(fieldName);
            case "PaymentAccount" -> Objects.equals("updatedAt", fieldName);
            case "Products" -> List.of("price", "updatedAt").contains(fieldName);
            case "Inventory" -> List.of("quantity", "updatedAt").contains(fieldName);
            case "Orders" -> List.of("status", "totalPrice", "updatedAt").contains(fieldName);
            case "OrdersItem" -> List.of("quantity", "price", "updatedAt").contains(fieldName);
            default -> false;
        };
    }

    public static void validate(Object object){
        if (object == null){
            throw new IllegalArgumentException("object is null");
        }
        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                String name = field.getName();

                if (value == null && !isNullableField(name, clazz.getSimpleName())) {
                    throw new IllegalArgumentException("field" + name + " must be not null");
                }
                if (value == null) continue;

                switch (value){
                    case String s when s.trim().isEmpty() ->
                        throw new IllegalArgumentException("field" + name + " must not be empty");
                    case String s when (name.equals("email") && !s.matches(EMAIL_REGEX)) ->
                        throw new IllegalArgumentException("field" +  name + " not match email");
                    case String s when (name.equals("phone") && !s.matches(PHONE_REGEX)) ->
                        throw new IllegalArgumentException("field" +  name + " not match phone");
                    case String s when (name.equals("name") && s.length() > 25) ->
                        throw new IllegalArgumentException("field" + name + " mustn't exceed 25 characters");
                    case Integer i when i <= 0 ->
                        throw new IllegalArgumentException("field" +  name + " must be positive");
                    case Double d when d <= 0 ->
                        throw new IllegalArgumentException("field" +  name + " must be positive");
                    default -> {}
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
