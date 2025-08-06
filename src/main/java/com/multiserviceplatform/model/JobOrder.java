package com.multiserviceplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_orders")
@Data
@Getter
@Setter
public class JobOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    @ManyToOne
    @JoinColumn(name = "seeker_id", nullable = false)
    private User seeker;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private ServiceProvider provider;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "Pending|Accepted|Completed|Canceled", message = "Invalid status")
    private String status;

    private LocalDateTime scheduledTime;

    @NotBlank(message = "Location is mandatory")
    private String location;

    @DecimalMin(value = "0.0", message = "Total price must be non-negative")
    private Float totalPrice;

    @NotNull
    @CreationTimestamp
    @Column(updatable = false)
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

    public void setProvider(ServiceProvider provider) {
        this.provider = provider;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public void setSeeker(User seeker) {
        this.seeker = seeker;
    }

    public void setService(Service service) {
        this.service = service;
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

    public ServiceProvider getProvider() {
        return provider;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public User getSeeker() {
        return seeker;
    }

    public Service getService() {
        return service;
    }

    public String getStatus() {
        return status;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }
}
