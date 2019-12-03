package com.example.backend.domain.repository

import com.example.backend.domain.models.PlanUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanUserRepository : JpaRepository<PlanUser, Int> {
    fun findByUserId(userId: Int) : List<PlanUser>
}