package com.example.backend.controllerImpl;

import com.example.backend.controller.OrderController;
import com.example.backend.domains.Order;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @Override
    @GetMapping("/date-range")
    public List<Order> getOrdersByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return orderService.getOrdersByDateRange(startDate, endDate);
    }

    @Override
    @GetMapping("/book-title")
    public List<Order> getOrdersByBookTitle(@RequestParam String title) {
        return orderService.getOrdersByBookTitle(title);
    }
}
