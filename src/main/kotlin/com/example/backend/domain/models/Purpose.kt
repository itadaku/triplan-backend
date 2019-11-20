package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "purposes")
data class Purpose (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "name")
    val name: String? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "updated_at")
    val updatedAt: Date? = null
)