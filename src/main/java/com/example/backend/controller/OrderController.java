package com.example.backend.controller;

import com.example.backend.domains.Order;
import com.example.backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
//    @ResponseBody
    public Order createOrder(@RequestBody Order order) {
        Order order1 = orderService.createOrder(order);
        System.out.println(order1);
        return order1;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}

