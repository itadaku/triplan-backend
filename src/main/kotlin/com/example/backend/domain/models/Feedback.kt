package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "feedbacks")
data class Feedback (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "user_id")
    val userId: Int? = null,
    @Column(name = "text")
    val text: String? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "updated_at")
    val updateAt: Date? = null
)