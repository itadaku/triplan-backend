package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "plan_reviews")
data class PlanReview (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "plan_id")
    val planId: Int? = null,
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