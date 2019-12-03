package com.example.backend.domain.service.impl

import com.example.backend.domain.models.PlanUser
import com.example.backend.domain.repository.PlanUserRepository
import com.example.backend.domain.service.PlanUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlanUserServiceImpl : PlanUserService{
    @Autowired
    lateinit var planUserRepository : PlanUserRepository

    fun save(planUser : PlanUser) : PlanUser {
        planUserRepository.save(planUser)
        return planUser
    }

    fun findByUserId(id: Int) : List<PlanUser> {
        return planUserRepository.findByUserId(id)
    }

    fun deleteById(id: Int) {
        planUserRepository.deleteById(id)
    }
}