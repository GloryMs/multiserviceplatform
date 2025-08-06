package com.multiserviceplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "services")
@Data
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private ServiceProvider provider;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ServiceCategory category;

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

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPricingModel(String pricingModel) {
        this.pricingModel = pricingModel;
    }

    public void setProvider(ServiceProvider provider) {
        this.provider = provider;
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

    public ServiceCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPricingModel() {
        return pricingModel;
    }

    public ServiceProvider getProvider() {
        return provider;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }
}