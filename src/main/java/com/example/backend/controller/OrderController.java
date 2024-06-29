package com.example.backend.controller;

import com.example.backend.dto.BookSalesDto;
import com.example.backend.domains.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderController {
    @GetMapping
    List<Order> getAllOrders();

    @PostMapping
    Order createOrder(@RequestBody Order order);

    @GetMapping("/date-range")
    List<Order> getOrdersByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate);

    @GetMapping("/book-title")
    List<Order> getOrdersByBookTitle(@RequestParam String title);

    @GetMapping("/sales")
    List<BookSalesDto> getBookSales(@RequestParam String startDate, @RequestParam String endDate);
}
