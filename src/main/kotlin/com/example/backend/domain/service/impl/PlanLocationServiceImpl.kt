package com.example.backend.domain.service.impl

import com.example.backend.domain.models.Plan
import com.example.backend.domain.models.PlanLocation
import com.example.backend.domain.repository.PlanLocationRepository
import com.example.backend.domain.service.PlanLocationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlanLocationServiceImpl : PlanLocationService {
    @Autowired
    lateinit var planLocationRepository : PlanLocationRepository

    fun save(planLocation : PlanLocation) : PlanLocation {
        planLocationRepository.save(planLocation)
        return planLocation
    }

    fun findByPlanId(id: Int) : List<PlanLocation> {
        return planLocationRepository.findByPlanId(id)
    }
}