package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.PaymentDTO;
import com.multiserviceplatform.model.JobOrder;
import com.multiserviceplatform.model.Payment;
import com.multiserviceplatform.model.User;
import com.multiserviceplatform.repository.JobOrderRepository;
import com.multiserviceplatform.repository.PaymentRepository;
import com.multiserviceplatform.repository.UserRepository;
import com.multiserviceplatform.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final JobOrderRepository jobOrderRepository;
    private final UserRepository userRepository;
    //private final ModelMapper modelMapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, JobOrderRepository jobOrderRepository,
                              UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.jobOrderRepository = jobOrderRepository;
        this.userRepository = userRepository;
        //this.modelMapper = modelMapper;
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        JobOrder job = jobOrderRepository.findById(paymentDTO.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + paymentDTO.getJobId()));
        User seeker = userRepository.findById(paymentDTO.getSeekerId())
                .orElseThrow(() -> new IllegalArgumentException("Seeker not found with ID: " + paymentDTO.getSeekerId()));
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setJob(job);
        payment.setSeeker(seeker);
        Payment savedPayment = paymentRepository.save(payment);
        return modelMapper.map(savedPayment, PaymentDTO.class);
    }

    @Override
    public PaymentDTO getPaymentById(Integer paymentId) {
        ModelMapper modelMapper = new ModelMapper();
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with ID: " + paymentId));
        return modelMapper.map(payment, PaymentDTO.class);
    }

    @Override
    public List<PaymentDTO> getPaymentsByJobId(Integer jobId) {
        ModelMapper modelMapper = new ModelMapper();
        return paymentRepository.findByJob_JobId(jobId).stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .collect(Collectors.toList());
    }
}
