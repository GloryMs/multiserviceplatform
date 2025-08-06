package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.DisputeDTO;
import com.multiserviceplatform.model.Admin;
import com.multiserviceplatform.model.Dispute;
import com.multiserviceplatform.model.JobOrder;
import com.multiserviceplatform.repository.AdminRepository;
import com.multiserviceplatform.repository.DisputeRepository;
import com.multiserviceplatform.repository.JobOrderRepository;
import com.multiserviceplatform.service.DisputeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DisputeServiceImpl implements DisputeService {
    private final DisputeRepository disputeRepository;
    private final JobOrderRepository jobOrderRepository;
    private final AdminRepository adminRepository;
    //private final ModelMapper modelMapper;

    @Autowired
    public DisputeServiceImpl(DisputeRepository disputeRepository, JobOrderRepository jobOrderRepository,
                              AdminRepository adminRepository) {
        this.disputeRepository = disputeRepository;
        this.jobOrderRepository = jobOrderRepository;
        this.adminRepository = adminRepository;
        //this.modelMapper = modelMapper;
    }

    @Override
    public DisputeDTO createDispute(DisputeDTO disputeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        JobOrder job = jobOrderRepository.findById(disputeDTO.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + disputeDTO.getJobId()));
        Admin admin = adminRepository.findById(disputeDTO.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with ID: " + disputeDTO.getAdminId()));
        Dispute dispute = modelMapper.map(disputeDTO, Dispute.class);
        dispute.setJob(job);
        dispute.setAdmin(admin);
        Dispute savedDispute = disputeRepository.save(dispute);
        return modelMapper.map(savedDispute, DisputeDTO.class);
    }

    @Override
    public DisputeDTO getDisputeById(Integer disputeId) {
        ModelMapper modelMapper = new ModelMapper();
        Dispute dispute = disputeRepository.findById(disputeId)
                .orElseThrow(() -> new IllegalArgumentException("Dispute not found with ID: " + disputeId));
        return modelMapper.map(dispute, DisputeDTO.class);
    }

    @Override
    public List<DisputeDTO> getDisputesByJobId(Integer jobId) {
        ModelMapper modelMapper = new ModelMapper();
        return disputeRepository.findByJob_JobId(jobId).stream()
                .map(dispute -> modelMapper.map(dispute, DisputeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DisputeDTO updateDispute(Integer disputeId, DisputeDTO disputeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Dispute dispute = disputeRepository.findById(disputeId)
                .orElseThrow(() -> new IllegalArgumentException("Dispute not found with ID: " + disputeId));
        JobOrder job = jobOrderRepository.findById(disputeDTO.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + disputeDTO.getJobId()));
        Admin admin = adminRepository.findById(disputeDTO.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with ID: " + disputeDTO.getAdminId()));
        modelMapper.map(disputeDTO, dispute);
        dispute.setJob(job);
        dispute.setAdmin(admin);
        dispute.setDisputeId(disputeId);
        Dispute updatedDispute = disputeRepository.save(dispute);
        return modelMapper.map(updatedDispute, DisputeDTO.class);
    }
}
