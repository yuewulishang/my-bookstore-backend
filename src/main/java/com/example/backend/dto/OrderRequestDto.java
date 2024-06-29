package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private Double totalPrice;
    private List<OrderItemDto> items;

    // Getters and setters
}