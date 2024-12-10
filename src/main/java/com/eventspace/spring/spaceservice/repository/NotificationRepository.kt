package com.eventspace.spring.spaceservice.repository

import com.eventspace.spring.spaceservice.model.entity.Notification
import com.eventspace.spring.spaceservice.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository: JpaRepository<Notification, Long> {
    fun findAllByForWhoAndIsRead(forWho: User, read: Boolean): List<Notification>
    fun countNotificationsByForWhoAndIsReadFalse(forWho: User): Long
}