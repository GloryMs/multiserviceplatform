package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.NotificationDTO;
import com.multiserviceplatform.model.JobOrder;
import com.multiserviceplatform.model.Notification;
import com.multiserviceplatform.model.User;
import com.multiserviceplatform.repository.JobOrderRepository;
import com.multiserviceplatform.repository.NotificationRepository;
import com.multiserviceplatform.repository.UserRepository;
import com.multiserviceplatform.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final JobOrderRepository jobOrderRepository;
    //private final ModelMapper modelMapper;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository,
                                   JobOrderRepository jobOrderRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.jobOrderRepository = jobOrderRepository;
        //this.modelMapper = modelMapper;
    }

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userRepository.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + notificationDTO.getUserId()));
        JobOrder job = notificationDTO.getJobId() != null ?
                jobOrderRepository.findById(notificationDTO.getJobId())
                        .orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + notificationDTO.getJobId()))
                : null;
        Notification notification = modelMapper.map(notificationDTO, Notification.class);
        notification.setUser(user);
        notification.setJob(job);
        notification.setSentAt(LocalDateTime.now());
        notification.setIsRead(false);
        Notification savedNotification = notificationRepository.save(notification);
        return modelMapper.map(savedNotification, NotificationDTO.class);
    }

    @Override
    public NotificationDTO getNotificationById(Integer notificationId) {
        ModelMapper modelMapper = new ModelMapper();
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found with ID: " + notificationId));
        return modelMapper.map(notification, NotificationDTO.class);
    }

    @Override
    public List<NotificationDTO> getNotificationsByUserId(Integer userId) {
        ModelMapper modelMapper = new ModelMapper();
        return notificationRepository.findByUser_UserId(userId).stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .collect(Collectors.toList());
    }
}
