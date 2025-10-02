package com.example.java.exercises.task9.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductParamRequest {
    private double fromPrice;
    private double toPrice;
    private boolean isAsc = true;
}
