package com.zosia.exam.controller;

import com.zosia.exam.domain.Genre;
import com.zosia.exam.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/api/genres")
    ResponseEntity<Genre> addGenre(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.addGenre(genre), HttpStatus.CREATED);
    }


}
