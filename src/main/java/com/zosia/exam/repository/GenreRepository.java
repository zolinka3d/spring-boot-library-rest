package com.zosia.exam.repository;

import com.zosia.exam.domain.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {

    Genre findByName(String name);
}
