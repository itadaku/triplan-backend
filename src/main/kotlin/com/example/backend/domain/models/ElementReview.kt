package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "element_reviews")
data class ElementReview (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "element_id")
    val elementId: Int? = null,
    @Column(name = "user_id")
    val userId: Int? = null,
    @Column(name = "title")
    val title: String? = null,
    @Column(name = "body")
    val body: String? = null,
    @Column(name = "evaluation")
    val evaluation: Float? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "updated_at")
    val updateAt: Date? = null
)