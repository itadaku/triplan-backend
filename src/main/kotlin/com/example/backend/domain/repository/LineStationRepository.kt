package com.example.backend.domain.repository

import com.example.backend.domain.models.LineStation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LineStationRepository : JpaRepository<LineStation, Int> {
}