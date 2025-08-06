package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.ServiceCategoryDTO;
import com.multiserviceplatform.model.ServiceCategory;
import com.multiserviceplatform.repository.ServiceCategoryRepository;
import com.multiserviceplatform.service.ServiceCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceCategoryServiceImpl implements ServiceCategoryService {
    private final ServiceCategoryRepository serviceCategoryRepository;
    //private final ModelMapper modelMapper;

    @Autowired
    public ServiceCategoryServiceImpl(ServiceCategoryRepository serviceCategoryRepository) {
        this.serviceCategoryRepository = serviceCategoryRepository;
        //this.modelMapper = modelMapper;
    }

    @Override
    public ServiceCategoryDTO createServiceCategory(ServiceCategoryDTO serviceCategoryDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ServiceCategory serviceCategory = modelMapper.map(serviceCategoryDTO, ServiceCategory.class);
        ServiceCategory savedCategory = serviceCategoryRepository.save(serviceCategory);
        return modelMapper.map(savedCategory, ServiceCategoryDTO.class);
    }

    @Override
    public ServiceCategoryDTO getServiceCategoryById(Integer categoryId) {
        ModelMapper modelMapper = new ModelMapper();
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Service category not found with ID: " + categoryId));
        return modelMapper.map(serviceCategory, ServiceCategoryDTO.class);
    }

    @Override
    public List<ServiceCategoryDTO> getAllServiceCategories() {
        ModelMapper modelMapper = new ModelMapper();
        return serviceCategoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, ServiceCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceCategoryDTO updateServiceCategory(Integer categoryId, ServiceCategoryDTO serviceCategoryDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Service category not found with ID: " + categoryId));
        modelMapper.map(serviceCategoryDTO, serviceCategory);
        serviceCategory.setCategoryId(categoryId);
        ServiceCategory updatedCategory = serviceCategoryRepository.save(serviceCategory);
        return modelMapper.map(updatedCategory, ServiceCategoryDTO.class);
    }

    @Override
    public void deleteServiceCategory(Integer categoryId) {
        if (!serviceCategoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("Service category not found with ID: " + categoryId);
        }
        serviceCategoryRepository.deleteById(categoryId);
    }
}
