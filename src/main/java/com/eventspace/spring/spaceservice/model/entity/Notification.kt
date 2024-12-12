package com.eventspace.spring.spaceservice.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "notifications", schema = "order_unit")
class Notification (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var isRead: Boolean = false,
    var content: String? = null,
    @ManyToOne
    var forWho: User? = null
)
