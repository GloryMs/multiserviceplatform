package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.DisputeDTO;

import java.util.List;

public interface DisputeService {
    DisputeDTO createDispute(DisputeDTO disputeDTO);
    DisputeDTO getDisputeById(Integer disputeId);
    List<DisputeDTO> getDisputesByJobId(Integer jobId);
    DisputeDTO updateDispute(Integer disputeId, DisputeDTO disputeDTO);
}
