package com.example.backend.domain.service.impl

import com.example.backend.domain.models.User
import com.example.backend.domain.repository.UserRepository
import com.example.backend.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @Autowired
    lateinit var userRepository : UserRepository

    fun save(user: User) : User {
        userRepository.save(user)
        return user
    }

    fun findByName(name: String?) : List<User> {
        return userRepository.findByName(name)
    }
}