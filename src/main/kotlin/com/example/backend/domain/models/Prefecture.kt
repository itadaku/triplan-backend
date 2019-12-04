package com.example.backend.domain.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "prefectures")
data class Prefecture (
    @Id
    @Column(name = "id")
    var id: Int? = null,
    @Column(name = "name")
    var name: String? = null,
    @Column(name = "agriculture")
    var agriculture: Int? = null,
    @Column(name = "forestry")
    var forestry: Int? = null,
    @Column(name = "fishing_industry")
    var fishingIndustry: Int? = null,
    @Column(name = "population")
    var population: String? = null,
    @Column(name = "created_at")
    var createdAt: Date? = null,
    @Column(name = "updated_at")
    var updatedAt: Date? = null
)