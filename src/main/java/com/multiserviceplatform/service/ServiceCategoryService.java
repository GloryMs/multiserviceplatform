package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.ServiceCategoryDTO;

import java.util.List;

public interface ServiceCategoryService {
    ServiceCategoryDTO createServiceCategory(ServiceCategoryDTO serviceCategoryDTO);
    ServiceCategoryDTO getServiceCategoryById(Integer categoryId);
    List<ServiceCategoryDTO> getAllServiceCategories();
    ServiceCategoryDTO updateServiceCategory(Integer categoryId, ServiceCategoryDTO serviceCategoryDTO);
    void deleteServiceCategory(Integer categoryId);
}
