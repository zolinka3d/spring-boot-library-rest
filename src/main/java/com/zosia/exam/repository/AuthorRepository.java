package com.zosia.exam.repository;

import com.zosia.exam.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Author findByName(String name);

    // with authors with books count and sort them by books count
    @Query("SELECT a, COUNT(b) FROM Author a LEFT JOIN a.books b GROUP BY a ORDER BY COUNT(b) DESC")
    List<Object[]> findAuthorsWithBookCountSorted();


}
