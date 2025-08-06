package com.multiserviceplatform.controller;

import com.multiserviceplatform.dto.PaymentDTO;
import com.multiserviceplatform.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SEEKER')")
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO createdPayment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.status(201).body(createdPayment);
    }

    @GetMapping("/{paymentId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Integer paymentId) {
        PaymentDTO payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByJobId(@PathVariable Integer jobId) {
        List<PaymentDTO> payments = paymentService.getPaymentsByJobId(jobId);
        return ResponseEntity.ok(payments);
    }
}
