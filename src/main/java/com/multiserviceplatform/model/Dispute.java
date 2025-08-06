package com.multiserviceplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "disputes")
@Data
@Getter
@Setter
public class Dispute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer disputeId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobOrder job;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "Open|Resolved|Escalated", message = "Invalid status")
    private String status;

    @NotNull
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisputeId(Integer disputeId) {
        this.disputeId = disputeId;
    }

    public void setJob(JobOrder job) {
        this.job = job;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Admin getAdmin() {
        return admin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDisputeId() {
        return disputeId;
    }

    public JobOrder getJob() {
        return job;
    }

    public String getStatus() {
        return status;
    }
}
