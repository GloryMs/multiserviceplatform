package com.multiserviceplatform.repository;

import com.multiserviceplatform.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByJob_JobId(Integer jobId);
}
