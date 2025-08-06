package com.multiserviceplatform.controller;


import com.multiserviceplatform.dto.ServiceCategoryDTO;
import com.multiserviceplatform.service.ServiceCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-categories")
public class ServiceCategoryController {
    private final ServiceCategoryService serviceCategoryService;

    @Autowired
    public ServiceCategoryController(ServiceCategoryService serviceCategoryService) {
        this.serviceCategoryService = serviceCategoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceCategoryDTO> createServiceCategory(@Valid @RequestBody ServiceCategoryDTO serviceCategoryDTO) {
        ServiceCategoryDTO createdCategory = serviceCategoryService.createServiceCategory(serviceCategoryDTO);
        return ResponseEntity.status(201).body(createdCategory);
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<ServiceCategoryDTO> getServiceCategoryById(@PathVariable Integer categoryId) {
        ServiceCategoryDTO category = serviceCategoryService.getServiceCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<List<ServiceCategoryDTO>> getAllServiceCategories() {
        List<ServiceCategoryDTO> categories = serviceCategoryService.getAllServiceCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceCategoryDTO> updateServiceCategory(@PathVariable Integer categoryId, @Valid @RequestBody ServiceCategoryDTO serviceCategoryDTO) {
        ServiceCategoryDTO updatedCategory = serviceCategoryService.updateServiceCategory(categoryId, serviceCategoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteServiceCategory(@PathVariable Integer categoryId) {
        serviceCategoryService.deleteServiceCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
