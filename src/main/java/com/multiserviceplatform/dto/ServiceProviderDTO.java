package com.multiserviceplatform.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderDTO {
    private Integer providerId;

    @NotNull(message = "User ID is mandatory")
    private Integer userId;

    @NotNull(message = "Verification status is mandatory")
    private Boolean isVerified;

    private String availabilityCalendar;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5")
    private Float ratingAverage;
}
