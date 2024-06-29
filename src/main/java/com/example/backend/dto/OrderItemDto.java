package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
class OrderItemDto {
    private Long bookId;
    private Integer quantity;
    private Double price;

    // Getters and setters
}