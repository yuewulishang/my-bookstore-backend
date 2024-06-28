package com.example.backend.controller;

import com.example.backend.domains.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/books")
public interface BookController {
    @GetMapping
    List<Book> getAllBooks();

    @GetMapping("/{id}")
    Book getBookById(@PathVariable Long id);

    @PostMapping
    Book createBook(@RequestBody Book book);

    @PutMapping("/{id}")
    Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails);

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id);
}
