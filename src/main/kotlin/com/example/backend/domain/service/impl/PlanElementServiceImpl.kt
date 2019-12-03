package com.example.backend.domain.service.impl

import com.example.backend.domain.models.PlanElement
import com.example.backend.domain.repository.PlanElementRepository
import com.example.backend.domain.service.PlanElementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlanElementServiceImpl : PlanElementService{
    @Autowired
    lateinit var planElementRepository : PlanElementRepository

    fun save(planElement : PlanElement) : PlanElement {
        planElementRepository.save(planElement)
        return planElement
    }
}