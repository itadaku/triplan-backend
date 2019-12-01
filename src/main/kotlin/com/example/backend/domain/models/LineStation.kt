package com.example.backend.domain.models

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "line_stations")
data class LineStation (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var id: Int? = null,
    @Column(name="line_id")
    var lineId: Int? = null,
    @Column(name="station_id")
    var stationId: Int? = null,
    @Column(name = "created_at")
    var createdAt: Date? = null,
    @Column(name = "updated_at")
    var updatedAt: Date? = null
)