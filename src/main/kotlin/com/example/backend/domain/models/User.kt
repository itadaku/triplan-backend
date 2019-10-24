package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "name")
    val name: String? = null,
    @Column(name = "email")
    val email: String? = null,
    @Column(name = "password")
    val password: String? = null,
    @Column(name = "line_station_id")
    val lineStationId: Int? = null,
    @Column(name = "age")
    val age: Int? = null,
    @Column(name = "gender")
    val gender: Int? = null,
    @Column(name = "token")
    val token: String? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "update_at")
    val updateAt: Date? = null
)