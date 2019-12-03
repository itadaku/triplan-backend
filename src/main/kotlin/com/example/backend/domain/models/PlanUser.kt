package com.example.backend.domain.models

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "plan_users")
data class PlanUser (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,
    @Column(name = "user_id")
    var userId: Int? = null,
    @Column(name = "plan_id")
    var planId: Int? = null,
    @Column(name = "is_progress")
    var isProgress: Boolean? = null,
    @Column(name = "now_progress_plan_element_id")
    var nowProgressPlanElementId: Int? = null,
    @Column(name = "created_at")
    var createdAt: Date? = null,
    @Column(name = "updated_at")
    var updateAt: Date? = null
)