package com.multiserviceplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class DisputeDTO {
    private Integer disputeId;
    @NotNull(message = "Job ID is mandatory")
    private Integer jobId;
    @NotNull(message = "Admin ID is mandatory")
    private Integer adminId;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "Open|Resolved|Escalated", message = "Invalid status")
    private String status;
    private LocalDateTime createdAt;

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAdminId() {
        return adminId;
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

    public Integer getJobId() {
        return jobId;
    }

    public String getStatus() {
        return status;
    }
}
