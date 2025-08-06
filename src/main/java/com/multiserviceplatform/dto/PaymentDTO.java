package com.multiserviceplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Integer paymentId;
    @NotNull(message = "Job ID is mandatory")
    private Integer jobId;
    @NotNull(message = "Seeker ID is mandatory")
    private Integer seekerId;
    @DecimalMin(value = "0.0", message = "Amount must be non-negative")
    private Float amount;
    @NotBlank(message = "Payment method is mandatory")
    @Pattern(regexp = "Card|Wallet", message = "Invalid payment method")
    private String paymentMethod;
    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "Pending|Completed|Refunded", message = "Invalid status")
    private String status;
    private LocalDateTime paymentDate;
    @DecimalMin(value = "0.0", message = "Commission must be non-negative")
    private Float commission;

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
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

    public void setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
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

    public Integer getJobId() {
        return jobId;
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

    public Integer getSeekerId() {
        return seekerId;
    }

    public String getStatus() {
        return status;
    }
}
