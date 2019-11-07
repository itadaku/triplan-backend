package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "plans")
data class Plan (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "title")
    val title: String? = null,
    @Column(name = "days_nights")
    val daysNights: Int? = null,
    @Column(name = "min_budget")
    val minBudget: Int? = null,
    @Column(name = "max_budget")
    val maxBudget: Int? = null,
    @Column(name = "number_of_people")
    val numberOfPeople: Int? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "update_at")
    val updateAt: Date? = null
)