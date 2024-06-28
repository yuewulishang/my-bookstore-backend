package com.example.backend.controllerImpl;

import com.example.backend.controller.OrderController;
import com.example.backend.domains.Order;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    public Order createOrder(Order order) {
        return orderService.createOrder(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
