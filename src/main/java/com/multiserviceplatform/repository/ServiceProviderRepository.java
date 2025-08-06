package com.multiserviceplatform.repository;

import com.multiserviceplatform.model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Integer> {
    Optional<ServiceProvider> findByUser_UserId(Integer userId);
}
