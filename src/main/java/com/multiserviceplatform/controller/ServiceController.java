package com.multiserviceplatform.controller;

import com.multiserviceplatform.dto.ServiceDTO;
import com.multiserviceplatform.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@Valid @RequestBody ServiceDTO serviceDTO) {
        ServiceDTO createdService = serviceService.createService(serviceDTO);
        return ResponseEntity.status(201).body(createdService);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Integer serviceId) {
        ServiceDTO service = serviceService.getServiceById(serviceId);
        return ResponseEntity.ok(service);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ServiceDTO>> getServicesByProviderId(@PathVariable Integer providerId) {
        List<ServiceDTO> services = serviceService.getServicesByProviderId(providerId);
        return ResponseEntity.ok(services);
    }
}
