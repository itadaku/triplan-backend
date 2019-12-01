package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "plan_users")
data class PlanUser (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "user_id")
    val userId: Int? = null,
    @Column(name = "plan_id")
    val planId: Int? = null,
    @Column(name = "is_progress")
    val isProgress: Boolean,
    @Column(name = "now_progress_plan_element_id")
    val nowProgressPlanElementId: Int? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "updated_at")
    val updateAt: Date? = null
)