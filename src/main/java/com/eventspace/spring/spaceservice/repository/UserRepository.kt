package com.eventspace.spring.spaceservice.repository

import com.eventspace.spring.spaceservice.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface UserRepository : JpaRepository<User, Long> {
    @Transactional(readOnly = true)
    fun findByEmail(email: String): User?

    @Transactional(readOnly = true)
    fun existsByEmail(email: String): Boolean

    @Transactional(readOnly = true)
    fun existsByPhoneNumber(phoneNumber: String): Boolean

    fun deleteUserById(userId: Long)
}