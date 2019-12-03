package com.example.backend.domain.models

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "plan_elements")
data class PlanElement (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,
    @Column(name = "name")
    var name: String? = null,
    @Column(name = "body")
    var body: String? = null,
    @Column(name = "link_url")
    var linkUrl: String? = null,
    @Column(name = "created_at")
    var createdAt: Date? = null,
    @Column(name = "updated_at")
    var updateAt: Date? = null
)