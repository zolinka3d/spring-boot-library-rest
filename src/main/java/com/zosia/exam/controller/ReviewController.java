package com.zosia.exam.controller;

import com.zosia.exam.domain.Review;
import com.zosia.exam.dto.ReviewDTO;
import com.zosia.exam.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/api/reviews")
    ResponseEntity<Review> addReview(@RequestBody ReviewDTO review) {
        return new ResponseEntity<>(reviewService.addReview(review), HttpStatus.CREATED);
    }

}
