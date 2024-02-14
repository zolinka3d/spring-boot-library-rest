package com.zosia.exam.service;


import com.zosia.exam.domain.Book;
import com.zosia.exam.domain.Review;
import com.zosia.exam.dto.ReviewDTO;
import com.zosia.exam.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    final ReviewRepository reviewRepository;
    final BookService bookService;

    public ReviewService(ReviewRepository reviewRepository, BookService bookService) {
        this.reviewRepository = reviewRepository;
        this.bookService = bookService;
    }

    public List<Review> getAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    public Review addReview(ReviewDTO reviewDTO) {
        Review review = new Review(reviewDTO.getContent(), reviewDTO.getRating());

        System.out.println(reviewDTO);

        int bookId = reviewDTO.getBookId();
        Optional<Book> book = bookService.getBook(bookId);
        book.ifPresent(value -> value.addReview(review));
        return reviewRepository.save(review);
    }
}
