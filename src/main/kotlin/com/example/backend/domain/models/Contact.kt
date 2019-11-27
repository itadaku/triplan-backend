package com.example.backend.domain.models

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "contacts")
data class Contact (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,
    @Column(name = "user_id")
    var userId: Int? = null,
    @Column(name = "text")
    var text: String? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "updated_at")
    val updateAt: Date? = null
)