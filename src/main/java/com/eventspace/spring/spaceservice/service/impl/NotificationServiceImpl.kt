package com.eventspace.spring.spaceservice.service.impl

import com.eventspace.spring.spaceservice.dto.NotificationDTO
import com.eventspace.spring.spaceservice.model.entity.Notification
import com.eventspace.spring.spaceservice.repository.NotificationRepository
import com.eventspace.spring.spaceservice.repository.UserRepository
import com.eventspace.spring.spaceservice.service.NotificationService
import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.NotFound
import org.springframework.web.server.ResponseStatusException

@Service
class NotificationServiceImpl(
    private val notificationRepository: NotificationRepository,
    private val userRepository: UserRepository,
) : NotificationService {
    override fun sendNotification(notificationDTO: NotificationDTO) {
        val user = userRepository.findByEmail(notificationDTO.receiverEmail) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND ,"User $notificationDTO.receiverEmail found")

        val notification = Notification(
            content = notificationDTO.content,
            forWho = user
        )

        notificationRepository.saveAndFlush(notification)
    }

    override fun getNotifications(userEmail: String, isRead: Boolean): List<Notification> {
        val user = userRepository.findByEmail(userEmail) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND ,"User $userEmail found")
        return notificationRepository.findAllByForWhoAndIsRead(user, isRead)
    }

    override fun readNotification(notificationId: Long) {
        val notification = notificationRepository.findById(notificationId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND ,"Notification $notificationId not found") }
        notification.isRead = true
        notificationRepository.saveAndFlush(notification)
    }

    override fun countUnreadNotifications(userEmail: String): Long {
        val user = userRepository.findByEmail(userEmail) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND ,"User $userEmail found")
        return notificationRepository.countNotificationsByForWhoAndIsReadFalse(user)
    }
}