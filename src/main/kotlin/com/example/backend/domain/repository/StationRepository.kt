package com.example.backend.domain.repository

import com.example.backend.domain.models.Station
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface StationRepository : JpaRepository<Station, Int> {
    fun findByName(name: String?): List<Station>
    fun findById(id: Int?): List<Station>
}