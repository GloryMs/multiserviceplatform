package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.JobOrderDTO;

import java.util.List;

public interface JobOrderService {
    JobOrderDTO createJobOrder(JobOrderDTO jobOrderDTO);
    JobOrderDTO getJobOrderById(Integer jobId);
    List<JobOrderDTO> getJobOrdersBySeekerId(Integer seekerId);
    List<JobOrderDTO> getJobOrdersByProviderId(Integer providerId);
    JobOrderDTO updateJobOrder(Integer jobId, JobOrderDTO jobOrderDTO);
}
