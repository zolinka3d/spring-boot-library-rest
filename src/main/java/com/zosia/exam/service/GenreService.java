package com.zosia.exam.service;


import com.zosia.exam.domain.Genre;
import com.zosia.exam.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GenreService {
    final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAll() {
        return (List<Genre>) genreRepository.findAll();
    }

    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }


}
