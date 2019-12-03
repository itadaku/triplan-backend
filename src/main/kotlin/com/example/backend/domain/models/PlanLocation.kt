package com.example.backend.domain.models

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "plan_locations")
data class PlanLocation (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,
    @Column(name = "plan_id")
    var planId: Int? = null,
    @Column(name = "location_id")
    var locationId: Int? = null,
    @Column(name = "created_at")
    var createdAt: Date? = null,
    @Column(name = "updated_at")
    var updateAt: Date? = null
)