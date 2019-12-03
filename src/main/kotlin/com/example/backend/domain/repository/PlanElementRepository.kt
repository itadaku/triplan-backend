package com.example.backend.domain.repository

import com.example.backend.domain.models.PlanElement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanElementRepository : JpaRepository<PlanElement, Int> {
}