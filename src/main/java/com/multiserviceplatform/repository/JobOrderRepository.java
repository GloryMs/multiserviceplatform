package com.multiserviceplatform.repository;

import com.multiserviceplatform.model.JobOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOrderRepository extends JpaRepository<JobOrder, Integer> {
    List<JobOrder> findBySeeker_UserId(Integer seekerId);
    List<JobOrder> findByProvider_ProviderId(Integer providerId);
}
