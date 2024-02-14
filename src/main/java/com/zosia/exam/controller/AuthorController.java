package com.zosia.exam.controller;

import com.zosia.exam.domain.Author;
import com.zosia.exam.dto.AuthorStatisticsDTO;
import com.zosia.exam.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/api/authors")
    ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.CREATED);
    }

    @GetMapping("/api/authors")
    ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/api/authors/{id}")
    ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        if (authorService.getAuthor(id).isEmpty()) return ResponseEntity.notFound().build();
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/authors/{id}")
    ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable int id) {
        if (authorService.getAuthor(id).isEmpty()) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(authorService.updateAuthor(author, id), HttpStatus.OK);
    }

    @GetMapping("/api/authors/{id}")
    ResponseEntity<Author> getAuthor(@PathVariable int id) {
        if (authorService.getAuthor(id).isEmpty()) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(authorService.getAuthor(id).orElse(null), HttpStatus.OK);
    }

@GetMapping("/api/authors/statistics")
    ResponseEntity<List<AuthorStatisticsDTO>> getAuthorsStatistics() {
        return new ResponseEntity<>(authorService.getAuthorsStatistics(), HttpStatus.OK);
    }

}
