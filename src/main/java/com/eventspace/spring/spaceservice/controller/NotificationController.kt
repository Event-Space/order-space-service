package com.eventspace.spring.spaceservice.controller

import com.eventspace.spring.spaceservice.dto.NotificationDTO
import com.eventspace.spring.spaceservice.model.entity.Notification
import com.eventspace.spring.spaceservice.service.NotificationService
import org.kenuki.securitymodule.annotations.SecureMe
import org.kenuki.securitymodule.sessions.SessionMe
import org.kenuki.securitymodule.util.Roles
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = ["*"])
class NotificationController(
    private val notificationService: NotificationService
) {
    @SecureMe(roles = [Roles.MANAGER])
    @PostMapping("/send")
    fun sendNotification(@RequestBody notificationDTO: NotificationDTO): ResponseEntity<Nothing> {
        notificationService.sendNotification(notificationDTO)
        return ResponseEntity.ok().build()
    }

    @SecureMe
    @GetMapping("/get")
    fun getNotifications(sessionMe: SessionMe, @RequestParam isRead: Boolean): List<Notification> {
        val email = sessionMe.getAttribute<String>("email") ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Этой ошибки не должно быть напиши Русику")
        return notificationService.getNotifications(email, isRead)
    }

    @GetMapping("/read")
    fun readNotifications(sessionMe: SessionMe, @RequestParam notificationId: Long): ResponseEntity<Nothing> {
        notificationService.readNotification(notificationId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/count-unread")
    fun countUnreadNotifications(sessionMe: SessionMe): Long {
        val email = sessionMe.getAttribute<String>("email") ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Этой ошибки не должно быть напиши Русику")
        return notificationService.countUnreadNotifications(email)
    }

}