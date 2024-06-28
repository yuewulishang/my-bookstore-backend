package com.example.backend.serviceImpl;

import com.example.backend.domains.Order;
import com.example.backend.repo.OrderRepository;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        order.getItems().forEach(item -> item.setOrder(order)); // 设置每个订单项的引用
        return orderRepository.save(order);
    }
}
