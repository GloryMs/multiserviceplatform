package com.multiserviceplatform.repository;

import com.multiserviceplatform.model.Dispute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisputeRepository extends JpaRepository<Dispute, Integer> {
    List<Dispute> findByJob_JobId(Integer jobId);
}
