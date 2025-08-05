package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.ServiceDTO;
import com.multiserviceplatform.model.Service;
import com.multiserviceplatform.model.ServiceProvider;
import com.multiserviceplatform.model.ServiceCategory;
import com.multiserviceplatform.repository.ServiceRepository;
import com.multiserviceplatform.repository.ServiceProviderRepository;
import com.multiserviceplatform.repository.ServiceCategoryRepository;
import com.multiserviceplatform.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceProviderRepository providerRepository;
    private final ServiceCategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceProviderRepository providerRepository,
                              ServiceCategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.serviceRepository = serviceRepository;
        this.providerRepository = providerRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        ServiceProvider provider = providerRepository.findById(serviceDTO.getProviderId())
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));
        ServiceCategory category = categoryRepository.findById(serviceDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        Service service = modelMapper.map(serviceDTO, Service.class);
        service.setProvider(provider);
        service.setCategory(category);
        Service savedService = serviceRepository.save(service);
        return modelMapper.map(savedService, ServiceDTO.class);
    }

    @Override
    public ServiceDTO getServiceById(Integer serviceId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));
        return modelMapper.map(service, ServiceDTO.class);
    }

    @Override
    public List<ServiceDTO> getServicesByProviderId(Integer providerId) {
        return serviceRepository.findByProvider_ProviderId(providerId).stream()
                .map(service -> modelMapper.map(service, ServiceDTO.class))
                .collect(Collectors.toList());
    }
}
