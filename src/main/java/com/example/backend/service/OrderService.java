package com.example.backend.service;

import com.example.backend.dto.BookSalesDto;
import com.example.backend.domains.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order createOrder(Order order);
    List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> getOrdersByBookTitle(String title);
    List<BookSalesDto> getBookSales(LocalDateTime startDate, LocalDateTime endDate);
}
