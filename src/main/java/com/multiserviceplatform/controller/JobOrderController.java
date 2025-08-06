package com.multiserviceplatform.controller;

import com.multiserviceplatform.dto.JobOrderDTO;
import com.multiserviceplatform.service.JobOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-orders")
public class JobOrderController {
    private final JobOrderService jobOrderService;

    @Autowired
    public JobOrderController(JobOrderService jobOrderService) {
        this.jobOrderService = jobOrderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SEEKER')")
    public ResponseEntity<JobOrderDTO> createJobOrder(@Valid @RequestBody JobOrderDTO jobOrderDTO) {
        JobOrderDTO createdJobOrder = jobOrderService.createJobOrder(jobOrderDTO);
        return ResponseEntity.status(201).body(createdJobOrder);
    }

    @GetMapping("/{jobId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<JobOrderDTO> getJobOrderById(@PathVariable Integer jobId) {
        JobOrderDTO jobOrder = jobOrderService.getJobOrderById(jobId);
        return ResponseEntity.ok(jobOrder);
    }

    @GetMapping("/seeker/{seekerId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'ADMIN')")
    public ResponseEntity<List<JobOrderDTO>> getJobOrdersBySeekerId(@PathVariable Integer seekerId) {
        List<JobOrderDTO> jobOrders = jobOrderService.getJobOrdersBySeekerId(seekerId);
        return ResponseEntity.ok(jobOrders);
    }

    @GetMapping("/provider/{providerId}")
    @PreAuthorize("hasAnyRole('PROVIDER', 'ADMIN')")
    public ResponseEntity<List<JobOrderDTO>> getJobOrdersByProviderId(@PathVariable Integer providerId) {
        List<JobOrderDTO> jobOrders = jobOrderService.getJobOrdersByProviderId(providerId);
        return ResponseEntity.ok(jobOrders);
    }

    @PutMapping("/{jobId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<JobOrderDTO> updateJobOrder(@PathVariable Integer jobId, @Valid @RequestBody JobOrderDTO jobOrderDTO) {
        JobOrderDTO updatedJobOrder = jobOrderService.updateJobOrder(jobId, jobOrderDTO);
        return ResponseEntity.ok(updatedJobOrder);
    }
}
