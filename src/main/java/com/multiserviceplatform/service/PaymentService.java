package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentById(Integer paymentId);
    List<PaymentDTO> getPaymentsByJobId(Integer jobId);
}
