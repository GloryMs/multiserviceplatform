package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO notificationDTO);
    NotificationDTO getNotificationById(Integer notificationId);
    List<NotificationDTO> getNotificationsByUserId(Integer userId);
}
