package com.example.backend.controller

import com.example.backend.domain.models.User
import com.example.backend.domain.models.response.TestResponse
import com.example.backend.domain.service.impl.UserServiceImpl
import com.example.backend.dto.response.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.MessageDigest
import java.util.Date
import kotlin.random.Random

@RestController
class UserController {
    @Autowired
    lateinit var userServiceImpl : UserServiceImpl

    private var TOKEN_LENGTH = 32
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun createToken(): String{
        return (1..TOKEN_LENGTH)
                .map { _ -> Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
    }

    fun passwordToHash(password: String?): String{
        return MessageDigest.getInstance("SHA-256")
                .digest(password?.toByteArray())
                .joinToString(separator="") {
                    "%02x".format(it)
                }
    }

    @GetMapping("/")
    fun testGet(): String {
        return "Hello World"
    }

    @GetMapping("/api/v1/test")
    fun testApi(): TestResponse {
        var testResponse = TestResponse()
        testResponse.message = "hello"
        testResponse.triplan_message = "hello triplan"
        return testResponse
    }

    @PostMapping("api/v1/user/register")
    fun registUser(@RequestBody user: User): User {
        val findByNameUsers = userServiceImpl.findByName(user.name)
        if(findByNameUsers.isNotEmpty()){
            throw CommonException("already exist user", HttpStatus.BAD_REQUEST)
        }
        val findByEmailUsers = userServiceImpl.findByEmail(user.email)
        if(findByEmailUsers.isNotEmpty()){
            throw CommonException("used email", HttpStatus.BAD_REQUEST)
        }

        user.createdAt = Date()
        user.updatedAt = Date()
        // パスワードをハッシュ化
        user.password = passwordToHash(user.password)
        // トークンを作成
        user.token = createToken()
        val saveUser = userServiceImpl.save(user)
        saveUser.id = null
        saveUser.password = null
        saveUser.createdAt = null
        saveUser.updatedAt = null
        return saveUser
    }

    @PostMapping("api/v1/user/login")
    fun loginUser(@RequestBody user: User): User {
        user.password = passwordToHash(user.password)
        val findUser = userServiceImpl.findByEmailAndPassword(user.email, user.password)
        // 見つからない場合の処理
        if(findUser.isEmpty()) {
            throw CommonException("email or password is wrong", HttpStatus.BAD_REQUEST)
        }

        val responseUser = findUser[0]
        responseUser.id = null
        responseUser.password = null
        responseUser.createdAt = null
        responseUser.updatedAt = null

        return responseUser
    }

    @GetMapping("api/v1/user")
    fun getUser(@RequestParam token: String): User{
        val findUser = userServiceImpl.findByToken(token)

        if(findUser.isEmpty()){
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        val responseUser = findUser[0]
        responseUser.id = null
        responseUser.password = null
        responseUser.createdAt = null
        responseUser.updatedAt = null

        return responseUser
    }

    @DeleteMapping("api/v1/user")
    fun deleteUser(@RequestParam token: String): Boolean{
        val findUser = userServiceImpl.findByToken(token)
        if(findUser.isEmpty()){
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }
        val intId = findUser[0].id
        if (intId != null) {
            userServiceImpl.deleteUser(intId)
        }

        val deletedUser = userServiceImpl.findByToken(token)
        return deletedUser.isEmpty()
    }

    @RequestMapping(path = ["api/v1/user"], method = [RequestMethod.PUT, RequestMethod.PATCH])
    fun updateUser(@RequestParam token: String, @RequestBody user: User): User {
        val findUser = userServiceImpl.findByToken(token)
        if(findUser.isEmpty()){
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        if(!user.name.isNullOrBlank()) findUser[0].name = user.name
        if(!user.password.isNullOrBlank()){
            user.password = passwordToHash(user.password)
            findUser[0].password = user.password
        }
        if(!user.email.isNullOrBlank()) {
            val emailFindUser = userServiceImpl.findByEmail(user.email)
            if(emailFindUser.isNotEmpty()){
                throw CommonException("Email Used", HttpStatus.BAD_REQUEST)
            }
            findUser[0].email = user.email
        }
        if(user.age != null) findUser[0].age = user.age
        if(user.gender != null) findUser[0].gender = user.gender
        if(user.lineStationId != null) findUser[0].lineStationId = user.lineStationId

        userServiceImpl.updateUser(findUser[0])

        val responseUser = findUser[0]
        responseUser.id = null
        responseUser.password = null
        responseUser.createdAt = null
        responseUser.updatedAt = null

        return responseUser
    }
}