package com.example.backend.controller

import com.example.backend.domain.models.User
import com.example.backend.domain.service.impl.UserServiceImpl
import com.example.backend.dto.response.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.ExceptionHandler
import java.security.MessageDigest
import java.util.Date
import javax.servlet.http.HttpServletRequest
import kotlin.random.Random

@RestController
class UserController {
    @Autowired
    lateinit var userServiceImpl : UserServiceImpl

    private var TOKEN_LENGTH = 32
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun createToken(): String{
        var randomString = (1..TOKEN_LENGTH)
                .map { _ -> Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")

        return randomString
    }

    @PostMapping("api/v1/user/register")
    fun registUser(@RequestBody user: User): User {
        var findByNameUsers = userServiceImpl.findByName(user.name)
        if(findByNameUsers.isNotEmpty()){
            throw AlreadyExistsException("already exist")
        }
        var findByEmailUsers = userServiceImpl.findByEmail(user.email)
        if(findByEmailUsers.isNotEmpty()){
            throw UsedEmailException("used email")
        }

        user.createdAt = Date()
        user.updatedAt = Date()
        // パスワードをハッシュ化
        user.password = MessageDigest.getInstance("SHA-256")
                .digest(user.password?.toByteArray())
                .joinToString(separator="") {
                    "%02x".format(it)
                }
        // トークンを作成
        user.token = createToken()
        var saveUser = userServiceImpl.save(user)
//        saveUser.password = null
        return saveUser
    }

    @PostMapping("api/v1/user/login")
    fun loginUser(@RequestBody user: User): User {
        user.password = MessageDigest.getInstance("SHA-256")
                .digest(user.password?.toByteArray())
                .joinToString(separator="") {
                    "%02x".format(it)
                }
        var findUser = userServiceImpl.findByEmailAndPassword(user.email, user.password)
        // 見つからない場合の処理
        if(findUser.isEmpty()) {
            throw UserIsNoneException("email or password is wrong")
        }
        return findUser[0]
    }

    @GetMapping("api/v1/user")
    fun getUser(@RequestParam token: String): User{
        var findUser = userServiceImpl.findByToken(token)

        if(findUser.isEmpty()){
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        return findUser[0]
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun userAlreadyExistsExeption(req: HttpServletRequest, error: AlreadyExistsException): ResponseEntity<ErrorResponse> {
        return ErrorResponse.createResponse(error)
    }

    @ExceptionHandler(UsedEmailException::class)
    fun usedEmailException(req: HttpServletRequest, error: UsedEmailException): ResponseEntity<ErrorResponse> {
        return ErrorResponse.createUsedEmailResponse(error)
    }

    @ExceptionHandler(UserIsNoneException::class)
    fun usedEmailException(req: HttpServletRequest, error: UserIsNoneException): ResponseEntity<ErrorResponse> {
        return ErrorResponse.createUserIsNoneResponse(error)
    }
}