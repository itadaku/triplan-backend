package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "plan_purposes")
data class PlanPurpose (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "plan_id")
    val planId: Int? = null,
    @Column(name = "purpose_id")
    val purposeId: Int? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "update_at")
    val updateAt: Date? = null
)