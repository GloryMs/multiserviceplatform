package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.ServiceProviderDTO;
import java.util.List;

public interface ServiceProviderService {
    ServiceProviderDTO createServiceProvider(ServiceProviderDTO serviceProviderDTO);
    ServiceProviderDTO getServiceProviderById(Integer providerId);
    List<ServiceProviderDTO> getAllServiceProviders();
    ServiceProviderDTO updateServiceProvider(Integer providerId, ServiceProviderDTO serviceProviderDTO);
    void deleteServiceProvider(Integer providerId);
}
