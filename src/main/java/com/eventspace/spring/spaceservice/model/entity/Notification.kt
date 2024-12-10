package com.eventspace.spring.spaceservice.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "notifications", schema = "order_unit")
class Notification (
    @Id
    var id: Long? = null,
    var isRead: Boolean = false,
    var content: String? = null,
    @ManyToOne
    var forWho: User? = null
)