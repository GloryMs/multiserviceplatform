package com.multiserviceplatform.repository;

import com.multiserviceplatform.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findByProvider_ProviderId(Integer providerId);
}
