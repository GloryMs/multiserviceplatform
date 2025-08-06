package com.multiserviceplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobOrder job;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "reviewed_id", nullable = false)
    private User reviewed;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating;

    private String comment;

    @NotNull
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    private Boolean isModerated = false;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModerated(Boolean moderated) {
        isModerated = moderated;
    }

    public void setJob(JobOrder job) {
        this.job = job;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setReviewed(User reviewed) {
        this.reviewed = reviewed;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
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

    public JobOrder getJob() {
        return job;
    }

    public Integer getRating() {
        return rating;
    }

    public User getReviewed() {
        return reviewed;
    }

    public User getReviewer() {
        return reviewer;
    }

    public Integer getReviewId() {
        return reviewId;
    }
}