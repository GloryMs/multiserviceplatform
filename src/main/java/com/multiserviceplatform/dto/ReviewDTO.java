package com.multiserviceplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {
    private Integer reviewId;
    @NotNull(message = "Job ID is mandatory")
    private Integer jobId;
    @NotNull(message = "Reviewer ID is mandatory")
    private Integer reviewerId;
    @NotNull(message = "Reviewed ID is mandatory")
    private Integer reviewedId;
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
    private Boolean isModerated;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModerated(Boolean moderated) {
        isModerated = moderated;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setReviewedId(Integer reviewedId) {
        this.reviewedId = reviewedId;
    }

    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getModerated() {
        return isModerated;
    }

    public Integer getJobId() {
        return jobId;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getReviewedId() {
        return reviewedId;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    public Integer getReviewId() {
        return reviewId;
    }
}
