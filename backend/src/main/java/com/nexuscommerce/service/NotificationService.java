package com.nexuscommerce.service;

import com.nexuscommerce.dto.notification.NotificationDto;
import com.nexuscommerce.dto.notification.SendNotificationRequest;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    List<NotificationDto> getUserNotifications(String userEmail);

    long getUnreadCount(String userEmail);

    NotificationDto markAsRead(String userEmail, UUID notificationId);

    void markAllAsRead(String userEmail);

    NotificationDto sendNotification(SendNotificationRequest request);
}
