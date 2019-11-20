package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "plan_elements")
data class PlanElement (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "name")
    val name: String? = null,
    @Column(name = "body")
    val body: String? = null,
    @Column(name = "link_url")
    val linkUrl: String? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "updated_at")
    val updateAt: Date? = null
)