package com.example.backend.serviceImpl;

import com.example.backend.domains.Book;
import com.example.backend.domains.Order;
import com.example.backend.domains.OrderItem;
import com.example.backend.dto.BookSalesDto;
import com.example.backend.repo.BookRepository;
import com.example.backend.repo.OrderRepository;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        order.setDate(LocalDateTime.now()); // 设置订单日期

        for (OrderItem item : order.getItems()) {
            Book book = bookRepository.findById(item.getBook().getBookId()).orElse(null);
            if (book == null) {
                throw new RuntimeException("Book not found");
            }
            if (book.getStock() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for book: " + book.getTitle());
            }
            book.setStock(book.getStock() - item.getQuantity());
            bookRepository.save(book);
            item.setOrder(order);
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findAllByDateBetween(startDate, endDate);
    }

    @Override
    public List<Order> getOrdersByBookTitle(String title) {
        return orderRepository.findAllByBookTitle(title);
    }

    @Override
    public List<BookSalesDto> getBookSales(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findAllByDateBetween(startDate, endDate);

        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getTitle(),
                        Collectors.summingLong(item -> item.getQuantity())
                ))
                .entrySet().stream()
                .map(entry -> new BookSalesDto(entry.getKey(), entry.getValue()))
                .sorted((a, b) -> Long.compare(b.getSales(), a.getSales()))
                .collect(Collectors.toList());
    }
}
