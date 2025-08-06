package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO getReviewById(Integer reviewId);
    List<ReviewDTO> getReviewsByJobId(Integer jobId);
    List<ReviewDTO> getReviewsByReviewedId(Integer reviewedId);
}
