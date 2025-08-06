package com.multiserviceplatform.controller;

import com.multiserviceplatform.dto.ReviewDTO;
import com.multiserviceplatform.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER')")
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.status(201).body(createdReview);
    }

    @GetMapping("/{reviewId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Integer reviewId) {
        ReviewDTO review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<List<ReviewDTO>> getReviewsByJobId(@PathVariable Integer jobId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByJobId(jobId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/reviewed/{reviewedId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<List<ReviewDTO>> getReviewsByReviewedId(@PathVariable Integer reviewedId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByReviewedId(reviewedId);
        return ResponseEntity.ok(reviews);
    }
}
