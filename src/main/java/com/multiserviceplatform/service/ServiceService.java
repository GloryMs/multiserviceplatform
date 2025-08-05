package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.ServiceDTO;

import java.util.List;

public interface ServiceService {
    ServiceDTO createService(ServiceDTO serviceDTO);
    ServiceDTO getServiceById(Integer serviceId);
    List<ServiceDTO> getServicesByProviderId(Integer providerId);
}
