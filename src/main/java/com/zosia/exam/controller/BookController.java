package com.zosia.exam.controller;

import com.zosia.exam.domain.Book;
import com.zosia.exam.dto.BookDTO;
import com.zosia.exam.dto.BookStatisticsDTO;
import com.zosia.exam.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/api/books")
    ResponseEntity<Book> addBook(@RequestBody BookDTO book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/api/books")
    ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/api/books/{id}")
    ResponseEntity<Book> getBook(@PathVariable int id) {
        if (bookService.getBook(id).isEmpty()) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(bookService.getBook(id).orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("/api/books/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable int id) {
        if(bookService.getBook(id).isEmpty()) return ResponseEntity.notFound().build();
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/books/{id}")
    ResponseEntity<Book> updateBook(@RequestBody BookDTO book, @PathVariable int id) {
        if (bookService.getBook(id).isEmpty()) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(bookService.updateBook(book, id), HttpStatus.OK);
    }

    @GetMapping("/api/books/sort")
    ResponseEntity<List<Book>> sortBooks(@RequestParam String sortBy) {
        return new ResponseEntity<>(bookService.sortBooks(sortBy), HttpStatus.OK);
    }

    @GetMapping("/api/books/statistics")
    ResponseEntity<List<BookStatisticsDTO>> getBooksStatistics() {
        return new ResponseEntity<>(bookService.getBooksStatistics(), HttpStatus.OK);
    }

    @GetMapping("/api/books/genre")
    ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre) {
        return new ResponseEntity<>(bookService.getBooksByGenre(genre), HttpStatus.OK);
    }

    @GetMapping("/api/books/search")
    ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Boolean sortByNewest,
            @RequestParam(required = false) Boolean sortByOldest,
            @RequestParam(required = false) Integer authorId
        ) {
        List<Book> books = bookService.searchBooks(title, genre, sortByNewest, sortByOldest, authorId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
