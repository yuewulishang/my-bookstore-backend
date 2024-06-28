package com.example.backend.controllerImpl;

import com.example.backend.controller.BookController;
import com.example.backend.domains.Book;
import com.example.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookControllerImpl implements BookController {

    @Autowired
    private BookService bookService;

    @Override
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Override
    public Book getBookById(Long id) {
        return bookService.getBookById(id);
    }

    @Override
    public Book createBook(Book book) {
        return bookService.createBook(book);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            bookDetails.setBookId(id);
            return bookService.updateBook(id, bookDetails);
        }
        return null; // or throw an exception
    }

    @Override
    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }
}
