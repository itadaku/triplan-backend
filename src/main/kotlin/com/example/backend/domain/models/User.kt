package com.example.backend.domain.models

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,
    @Column(name = "name")
    var name: String? = null,
    @Column(name = "email")
    var email: String? = null,
    @Column(name = "password")
    var password: String? = null,
    @Column(name = "line_station_id")
    var lineStationId: Int? = null,
    @Column(name = "age")
    var age: Int? = null,
    @Column(name = "gender")
    var gender: Int? = null,
    @Column(name = "token")
    var token: String? = null,
    @Column(name = "created_at")
    var createdAt: Date? = null,
    @Column(name = "updated_at")
    var updatedAt: Date? = null
)