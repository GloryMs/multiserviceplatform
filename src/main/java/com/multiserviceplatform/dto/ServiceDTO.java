package com.multiserviceplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ServiceDTO {
    private Integer serviceId;
    private Integer providerId;
    private Integer categoryId;
    @NotBlank(message = "Service name is mandatory")
    private String serviceName;
    private String description;
    @DecimalMin(value = "0.0", message = "Price must be non-negative")
    private Float basePrice;
    @NotBlank(message = "Pricing model is mandatory")
    @Pattern(regexp = "flat|hourly", message = "Pricing model must be flat or hourly")
    private String pricingModel;

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPricingModel(String pricingModel) {
        this.pricingModel = pricingModel;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public String getPricingModel() {
        return pricingModel;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }
}
