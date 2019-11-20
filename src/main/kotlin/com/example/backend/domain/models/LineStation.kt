package com.example.backend.domain.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "line_stations")
data class LineStation (
    @Id
    @GeneratedValue
    @Column(name="id")
    val id: Int? = null,
    @Column(name="line_id")
    val lineId: Int? = null,
    @Column(name="station_id")
    val stationId: Int? = null,
    @Column(name = "created_at")
    val createdAt: Date? = null,
    @Column(name = "updated_at")
    val updateAt: Date? = null
)