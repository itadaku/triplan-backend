package com.example.backend.controller

import com.example.backend.domain.models.User
import com.example.backend.domain.service.impl.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.security.MessageDigest
import java.util.Date

@RestController
class UserController {
    @Autowired
    lateinit var userServiceImpl : UserServiceImpl

    @PostMapping("api/v1/user/register")
    fun registUser(@RequestBody user: User): User {
        user.createdAt = Date()
        user.updatedAt = Date()
        // パスワードをハッシュ化
        user.password = MessageDigest.getInstance("SHA-256")
                .digest(user.password?.toByteArray())
                .joinToString(separator="") {
                    "%02x".format(it)
                }
        // トークンを作成
        user.token = "TEST_TOKEN"
        var saveUser = userServiceImpl.save(user)
//        saveUser.password = null
        return saveUser
    }
}