package com.example.backend.domain.service.impl

import com.example.backend.domain.models.Plan
import com.example.backend.domain.repository.PlanRepository
import com.example.backend.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlanServiceImpl : UserService {
    @Autowired
    lateinit var planRepository : PlanRepository

    fun save(plan : Plan) : Plan {
        planRepository.save(plan)
        return plan
    }

    fun findById(id: Int) : Optional<Plan> {
        return planRepository.findById(id)
    }
}