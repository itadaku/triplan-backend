package com.example.backend.domain.repository

import com.example.backend.domain.models.Line
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LineRepository : JpaRepository<Line, Int> {
    fun findByName(name: String?): List<Line>
}