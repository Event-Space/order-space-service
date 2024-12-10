package com.eventspace.spring.spaceservice.service

import com.eventspace.spring.spaceservice.dto.NotificationDTO
import com.eventspace.spring.spaceservice.model.entity.Notification

interface NotificationService {
    fun sendNotification(notificationDTO: NotificationDTO)
    fun getNotifications(userEmail: String, isRead: Boolean): List<Notification>
    fun readNotification(notificationId: Long)
    fun countUnreadNotifications(userEmail: String): Long
}