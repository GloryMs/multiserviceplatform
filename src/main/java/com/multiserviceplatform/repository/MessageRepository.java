package com.multiserviceplatform.repository;

import com.multiserviceplatform.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByJob_JobId(Integer jobId);
}
