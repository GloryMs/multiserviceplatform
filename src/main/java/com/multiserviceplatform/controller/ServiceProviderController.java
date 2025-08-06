package com.multiserviceplatform.controller;

import com.multiserviceplatform.dto.ServiceProviderDTO;
import com.multiserviceplatform.service.ServiceProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-providers")
public class ServiceProviderController {
    private final ServiceProviderService serviceProviderService;

    @Autowired
    public ServiceProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PROVIDER', 'ADMIN')")
    public ResponseEntity<ServiceProviderDTO> createServiceProvider(@Valid @RequestBody ServiceProviderDTO serviceProviderDTO) {
        ServiceProviderDTO createdProvider = serviceProviderService.createServiceProvider(serviceProviderDTO);
        return ResponseEntity.status(201).body(createdProvider);
    }

    @GetMapping("/{providerId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<ServiceProviderDTO> getServiceProviderById(@PathVariable Integer providerId) {
        ServiceProviderDTO provider = serviceProviderService.getServiceProviderById(providerId);
        return ResponseEntity.ok(provider);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<List<ServiceProviderDTO>> getAllServiceProviders() {
        List<ServiceProviderDTO> providers = serviceProviderService.getAllServiceProviders();
        return ResponseEntity.ok(providers);
    }

    @PutMapping("/{providerId}")
    @PreAuthorize("hasAnyRole('PROVIDER', 'ADMIN')")
    public ResponseEntity<ServiceProviderDTO> updateServiceProvider(@PathVariable Integer providerId, @Valid @RequestBody ServiceProviderDTO serviceProviderDTO) {
        ServiceProviderDTO updatedProvider = serviceProviderService.updateServiceProvider(providerId, serviceProviderDTO);
        return ResponseEntity.ok(updatedProvider);
    }

    @DeleteMapping("/{providerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteServiceProvider(@PathVariable Integer providerId) {
        serviceProviderService.deleteServiceProvider(providerId);
        return ResponseEntity.noContent().build();
    }
}
