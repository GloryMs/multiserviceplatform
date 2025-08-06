package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.JobOrderDTO;
import com.multiserviceplatform.model.JobOrder;
import com.multiserviceplatform.model.Service;
import com.multiserviceplatform.model.ServiceProvider;
import com.multiserviceplatform.model.User;
import com.multiserviceplatform.repository.JobOrderRepository;
import com.multiserviceplatform.repository.ServiceProviderRepository;
import com.multiserviceplatform.repository.ServiceRepository;
import com.multiserviceplatform.repository.UserRepository;
import com.multiserviceplatform.service.JobOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Transactional
public class JobOrderServiceImpl implements JobOrderService {
    private final JobOrderRepository jobOrderRepository;
    private final UserRepository userRepository;
    private final ServiceProviderRepository serviceProviderRepository;
    private final ServiceRepository serviceRepository;
    //private final ModelMapper modelMapper;

    @Autowired
    public JobOrderServiceImpl(JobOrderRepository jobOrderRepository, UserRepository userRepository,
                               ServiceProviderRepository serviceProviderRepository, ServiceRepository serviceRepository) {
        this.jobOrderRepository = jobOrderRepository;
        this.userRepository = userRepository;
        this.serviceProviderRepository = serviceProviderRepository;
        this.serviceRepository = serviceRepository;
        //this.modelMapper = modelMapper;
    }

    @Override
    public JobOrderDTO createJobOrder(JobOrderDTO jobOrderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User seeker = userRepository.findById(jobOrderDTO.getSeekerId())
                .orElseThrow(() -> new IllegalArgumentException("Seeker not found with ID: " + jobOrderDTO.getSeekerId()));
        Service service = serviceRepository.findById(jobOrderDTO.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + jobOrderDTO.getServiceId()));
        ServiceProvider provider = jobOrderDTO.getProviderId() != null ?
                serviceProviderRepository.findById(jobOrderDTO.getProviderId())
                        .orElseThrow(() -> new IllegalArgumentException("Provider not found with ID: " + jobOrderDTO.getProviderId()))
                : null;
        JobOrder jobOrder = modelMapper.map(jobOrderDTO, JobOrder.class);
        jobOrder.setSeeker(seeker);
        jobOrder.setService(service);
        jobOrder.setProvider(provider);
        LocalDateTime dateTime = LocalDateTime.parse(jobOrderDTO.getScheduledTime().toString());
        jobOrder.setScheduledTime(dateTime);
        jobOrder.setCreatedAt(LocalDateTime.now());// Must be checked that it will be added by the DB.
        JobOrder savedJobOrder = jobOrderRepository.save(jobOrder);
        return modelMapper.map(savedJobOrder, JobOrderDTO.class);
    }

    @Override
    public JobOrderDTO getJobOrderById(Integer jobId) {
        ModelMapper modelMapper = new ModelMapper();
        JobOrder jobOrder = jobOrderRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job order not found with ID: " + jobId));
        return modelMapper.map(jobOrder, JobOrderDTO.class);
    }

    @Override
    public List<JobOrderDTO> getJobOrdersBySeekerId(Integer seekerId) {
        ModelMapper modelMapper = new ModelMapper();
        return jobOrderRepository.findBySeeker_UserId(seekerId).stream()
                .map(jobOrder -> modelMapper.map(jobOrder, JobOrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobOrderDTO> getJobOrdersByProviderId(Integer providerId) {
        ModelMapper modelMapper = new ModelMapper();
        return jobOrderRepository.findByProvider_ProviderId(providerId).stream()
                .map(jobOrder -> modelMapper.map(jobOrder, JobOrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobOrderDTO updateJobOrder(Integer jobId, JobOrderDTO jobOrderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        JobOrder jobOrder = jobOrderRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job order not found with ID: " + jobId));
        User seeker = userRepository.findById(jobOrderDTO.getSeekerId())
                .orElseThrow(() -> new IllegalArgumentException("Seeker not found with ID: " + jobOrderDTO.getSeekerId()));
        Service service = serviceRepository.findById(jobOrderDTO.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + jobOrderDTO.getServiceId()));
        ServiceProvider provider = jobOrderDTO.getProviderId() != null ?
                serviceProviderRepository.findById(jobOrderDTO.getProviderId())
                        .orElseThrow(() -> new IllegalArgumentException("Provider not found with ID: " + jobOrderDTO.getProviderId()))
                : null;
        modelMapper.map(jobOrderDTO, jobOrder);
        jobOrder.setSeeker(seeker);
        jobOrder.setService(service);
        jobOrder.setProvider(provider);
        jobOrder.setJobId(jobId);
        jobOrder.setCreatedAt(LocalDateTime.now());
        JobOrder updatedJobOrder = jobOrderRepository.save(jobOrder);
        return modelMapper.map(updatedJobOrder, JobOrderDTO.class);
    }
}
