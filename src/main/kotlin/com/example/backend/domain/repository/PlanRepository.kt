package com.example.backend.domain.repository

import com.example.backend.domain.models.Plan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanRepository : JpaRepository<Plan, Int> {

}