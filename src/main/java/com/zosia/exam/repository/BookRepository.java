package com.zosia.exam.repository;

import com.zosia.exam.domain.Book;
import com.zosia.exam.domain.Genre;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    // find all by order by title
    Iterable<Book> findAllByOrderByTitle();

    // find all by order by release date
    Iterable<Book> findAllByOrderByReleaseDate();

    // find all by release date desc
    Iterable<Book> findAllByOrderByReleaseDateDesc();

    // find all by order by author
    Iterable<Book> findAllByOrderByAuthorName();

//    @Query("SELECT b, AVG(r.rating) FROM Book b LEFT JOIN b.reviews r GROUP BY b")
//    List<Object[]> findAverageRatingForEachBook();

    // find books with count of reviews and average rating
    @Query("SELECT b, COUNT(r), AVG(r.rating) FROM Book b LEFT JOIN b.reviews r GROUP BY b")
    List<Object[]> findBooksWithCountOfReviewsAndAverageRating();



    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g IN :genres")
    Iterable<Book> findByGenres(@Param("genres") List<Genre> genres);

    // find books by author
    Iterable<Book> findByAuthorId(int id);

}

