package com.example.backend.controller;

import com.example.backend.domains.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public interface OrderController {

    @PostMapping
    Order createOrder(@RequestBody Order order);

    @GetMapping
    List<Order> getAllOrders();
}
