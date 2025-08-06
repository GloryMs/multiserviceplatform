package com.multiserviceplatform.repository;

import com.multiserviceplatform.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByJob_JobId(Integer jobId);
    List<Review> findByReviewed_UserId(Integer reviewedId);
}
