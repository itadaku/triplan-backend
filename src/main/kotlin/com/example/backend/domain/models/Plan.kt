package com.example.backend.domain.models

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "plans")
data class Plan (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "title")
    var title: String? = null,
    @Column(name = "days_nights")
    var daysNights: Int? = null,
    @Column(name = "min_budget")
    var minBudget: Int? = null,
    @Column(name = "max_budget")
    var maxBudget: Int? = null,
    @Column(name = "number_of_people")
    var numberOfPeople: Int? = null,
    @Column(name = "created_at")
    var createdAt: Date? = null,
    @Column(name = "updated_at")
    var updateAt: Date? = null
)