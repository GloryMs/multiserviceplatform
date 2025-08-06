package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.ServiceProviderDTO;
import com.multiserviceplatform.model.ServiceProvider;
import com.multiserviceplatform.model.User;
import com.multiserviceplatform.model.ServiceCategory;
import com.multiserviceplatform.repository.ServiceProviderRepository;
import com.multiserviceplatform.repository.UserRepository;
import com.multiserviceplatform.repository.ServiceCategoryRepository;
import com.multiserviceplatform.service.ServiceProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceProviderServiceImpl implements ServiceProviderService {
    private final ServiceProviderRepository serviceProviderRepository;
    private final UserRepository userRepository;
    //private final ModelMapper modelMapper;

    @Autowired
    public ServiceProviderServiceImpl(ServiceProviderRepository serviceProviderRepository,
                                      UserRepository userRepository,
                                      ServiceCategoryRepository serviceCategoryRepository) {
        this.serviceProviderRepository = serviceProviderRepository;
        this.userRepository = userRepository;
        //this.modelMapper = modelMapper;
    }

    @Override
    public ServiceProviderDTO createServiceProvider(ServiceProviderDTO serviceProviderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userRepository.findById(serviceProviderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + serviceProviderDTO.getUserId()));
        if (!"Provider".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("User must have PROVIDER role to become a service provider");
        }
        if (serviceProviderRepository.findByUser_UserId(serviceProviderDTO.getUserId()).isPresent()) {
            throw new IllegalArgumentException("Service provider already exists for user ID: " + serviceProviderDTO.getUserId());
        }
        ServiceProvider serviceProvider = modelMapper.map(serviceProviderDTO, ServiceProvider.class);
        serviceProvider.setUser(user);
        ServiceProvider savedProvider = serviceProviderRepository.save(serviceProvider);
        user.setServiceProvider(serviceProvider);
        userRepository.save(user);
        return modelMapper.map(savedProvider, ServiceProviderDTO.class);
    }

    @Override
    public ServiceProviderDTO getServiceProviderById(Integer providerId) {
        ModelMapper modelMapper = new ModelMapper();
        ServiceProvider serviceProvider = serviceProviderRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Service provider not found with ID: " + providerId));
        return modelMapper.map(serviceProvider, ServiceProviderDTO.class);
    }

    @Override
    public List<ServiceProviderDTO> getAllServiceProviders() {
        ModelMapper modelMapper = new ModelMapper();
        return serviceProviderRepository.findAll().stream()
                .map(provider -> modelMapper.map(provider, ServiceProviderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceProviderDTO updateServiceProvider(Integer providerId, ServiceProviderDTO serviceProviderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ServiceProvider serviceProvider = serviceProviderRepository.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Service provider not found with ID: " + providerId));
        User user = userRepository.findById(serviceProviderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + serviceProviderDTO.getUserId()));
        if (!"Provider".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("User must have PROVIDER role to update service provider");
        }
        modelMapper.map(serviceProviderDTO, serviceProvider);
        serviceProvider.setUser(user);
        serviceProvider.setProviderId(providerId);
        ServiceProvider updatedProvider = serviceProviderRepository.save(serviceProvider);
        return modelMapper.map(updatedProvider, ServiceProviderDTO.class);
    }

    @Override
    public void deleteServiceProvider(Integer providerId) {
        if (!serviceProviderRepository.existsById(providerId)) {
            throw new IllegalArgumentException("Service provider not found with ID: " + providerId);
        }
        serviceProviderRepository.deleteById(providerId);
    }
}
