package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.ReviewDTO;
import com.multiserviceplatform.model.JobOrder;
import com.multiserviceplatform.model.Review;
import com.multiserviceplatform.model.User;
import com.multiserviceplatform.repository.JobOrderRepository;
import com.multiserviceplatform.repository.ReviewRepository;
import com.multiserviceplatform.repository.UserRepository;
import com.multiserviceplatform.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final JobOrderRepository jobOrderRepository;
    private final UserRepository userRepository;
    //private final ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, JobOrderRepository jobOrderRepository,
                             UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.jobOrderRepository = jobOrderRepository;
        this.userRepository = userRepository;
        //this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        ModelMapper modelMapper = new ModelMapper();
        JobOrder job = jobOrderRepository.findById(reviewDTO.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + reviewDTO.getJobId()));
        User reviewer = userRepository.findById(reviewDTO.getReviewerId())
                .orElseThrow(() -> new IllegalArgumentException("Reviewer not found with ID: " + reviewDTO.getReviewerId()));
        User reviewed = userRepository.findById(reviewDTO.getReviewedId())
                .orElseThrow(() -> new IllegalArgumentException("Reviewed user not found with ID: " + reviewDTO.getReviewedId()));
        Review review = modelMapper.map(reviewDTO, Review.class);
        review.setJob(job);
        review.setReviewer(reviewer);
        review.setReviewed(reviewed);
        Review savedReview = reviewRepository.save(review);
        return modelMapper.map(savedReview, ReviewDTO.class);
    }

    @Override
    public ReviewDTO getReviewById(Integer reviewId) {
        ModelMapper modelMapper = new ModelMapper();
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + reviewId));
        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    public List<ReviewDTO> getReviewsByJobId(Integer jobId) {
        ModelMapper modelMapper = new ModelMapper();
        return reviewRepository.findByJob_JobId(jobId).stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByReviewedId(Integer reviewedId) {
        ModelMapper modelMapper = new ModelMapper();
        return reviewRepository.findByReviewed_UserId(reviewedId).stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }
}
