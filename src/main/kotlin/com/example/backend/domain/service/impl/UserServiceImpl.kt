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

    fun findByEmail(email: String?) : List<User>{
        return userRepository.findByEmail(email)
    }

    fun findByEmailAndPassword(email: String?, password: String?) : List<User> {
        return userRepository.findByEmailAndPassword(email, password)
    }

    fun findByToken(token: String?) : List<User> {
        return userRepository.findByToken(token)
    }

    fun deleteUser(id: Int) {
        userRepository.deleteById(id)
    }

    fun updateUser(user: User){
        userRepository.save(user)
    }
}