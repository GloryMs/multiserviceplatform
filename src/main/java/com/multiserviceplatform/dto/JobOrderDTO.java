package com.multiserviceplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class JobOrderDTO {
    private Integer jobId;
    @NotNull(message = "Seeker ID is mandatory")
    private Integer seekerId;
    private Integer providerId;
    @NotNull(message = "Service ID is mandatory")
    private Integer serviceId;
    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "Pending|Accepted|Completed|Canceled", message = "Invalid status")
    private String status;
    private LocalDateTime scheduledTime;
    @NotBlank(message = "Location is mandatory")
    private String location;
    @DecimalMin(value = "0.0", message = "Total price must be non-negative")
    private Float totalPrice;
    private LocalDateTime createdAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public void setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getJobId() {
        return jobId;
    }

    public String getLocation() {
        return location;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public Integer getSeekerId() {
        return seekerId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public String getStatus() {
        return status;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }
}
