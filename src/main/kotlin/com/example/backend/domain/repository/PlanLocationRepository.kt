package com.example.backend.domain.repository

import com.example.backend.domain.models.PlanLocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanLocationRepository : JpaRepository<PlanLocation, Int> {
    fun findByPlanId(planId: Int) : List<PlanLocation>
}