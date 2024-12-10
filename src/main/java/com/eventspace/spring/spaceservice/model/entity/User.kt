package com.eventspace.spring.spaceservice.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "users", schema = "user_unit")
class User {
    @Id
    var id: Long? = null

    var email: String? = null

    var firstName: String? = null

    var lastName: String? = null

    var password: String? = null

    var phoneNumber: String? = null
}