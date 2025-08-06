package com.multiserviceplatform.controller;

import com.multiserviceplatform.dto.DisputeDTO;
import com.multiserviceplatform.service.DisputeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disputes")
public class DisputeController {
    private final DisputeService disputeService;

    @Autowired
    public DisputeController(DisputeService disputeService) {
        this.disputeService = disputeService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER')")
    public ResponseEntity<DisputeDTO> createDispute(@Valid @RequestBody DisputeDTO disputeDTO) {
        DisputeDTO createdDispute = disputeService.createDispute(disputeDTO);
        return ResponseEntity.status(201).body(createdDispute);
    }

    @GetMapping("/{disputeId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<DisputeDTO> getDisputeById(@PathVariable Integer disputeId) {
        DisputeDTO dispute = disputeService.getDisputeById(disputeId);
        return ResponseEntity.ok(dispute);
    }

    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<List<DisputeDTO>> getDisputesByJobId(@PathVariable Integer jobId) {
        List<DisputeDTO> disputes = disputeService.getDisputesByJobId(jobId);
        return ResponseEntity.ok(disputes);
    }

    @PutMapping("/{disputeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DisputeDTO> updateDispute(@PathVariable Integer disputeId, @Valid @RequestBody DisputeDTO disputeDTO) {
        DisputeDTO updatedDispute = disputeService.updateDispute(disputeId, disputeDTO);
        return ResponseEntity.ok(updatedDispute);
    }
}
