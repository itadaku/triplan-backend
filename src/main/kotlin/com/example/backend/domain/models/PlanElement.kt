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
    @Column(name = "plan_id")
    var plan_id: Int? = null,
    @Column(name = "element_id")
    var element_id: Int? = null,
    @Column(name = "seq")
    var seq: Int? = null,
    @Column(name = "from_date")
    var from_date: Date? = null,
    @Column(name = "to_date")
    var to_date: Date? = null,
    @Column(name = "created_at")
    var createdAt: Date? = null,
    @Column(name = "updated_at")
    var updateAt: Date? = null
)