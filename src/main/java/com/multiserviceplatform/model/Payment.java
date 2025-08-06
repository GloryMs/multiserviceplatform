package com.multiserviceplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobOrder job;

    @ManyToOne
    @JoinColumn(name = "seeker_id", nullable = false)
    private User seeker;

    @DecimalMin(value = "0.0", message = "Amount must be non-negative")
    private Float amount;

    @NotBlank(message = "Payment method is mandatory")
    @Pattern(regexp = "Card|Wallet", message = "Invalid payment method")
    private String paymentMethod;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "Pending|Completed|Refunded", message = "Invalid status")
    private String status;

    @NotNull
    private LocalDateTime paymentDate;

    @DecimalMin(value = "0.0", message = "Commission must be non-negative")
    private Float commission;

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public void setJob(JobOrder job) {
        this.job = job;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setSeeker(User seeker) {
        this.seeker = seeker;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getAmount() {
        return amount;
    }

    public Float getCommission() {
        return commission;
    }

    public JobOrder getJob() {
        return job;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public User getSeeker() {
        return seeker;
    }

    public String getStatus() {
        return status;
    }
}
