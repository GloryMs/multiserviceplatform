package com.multiserviceplatform.repository;

import com.multiserviceplatform.model.Admin;
import com.multiserviceplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    boolean existsByUser(User user);
}
