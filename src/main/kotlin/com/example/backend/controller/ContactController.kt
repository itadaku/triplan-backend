package com.example.backend.controller

import com.example.backend.domain.models.Contact
import com.example.backend.domain.models.response.DoneResponse
import com.example.backend.domain.service.impl.ContactServiceImpl
import com.example.backend.domain.service.impl.UserServiceImpl
import com.example.backend.dto.response.CommonException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
class ContactController {
    @Autowired
    lateinit var userServiceImpl : UserServiceImpl

    @Autowired
    lateinit var contactServiceImpl : ContactServiceImpl

    @PostMapping("api/v1/app/contact")
    fun sendContact(@RequestParam token: String, @RequestBody contact: Contact): DoneResponse {
        val findUser = userServiceImpl.findByToken(token)
        if(findUser.isEmpty()) {
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        contact.createdAt = Date()
        contact.updateAt = Date()
        contact.userId = findUser[0].id

        contactServiceImpl.save(contact)

        var doneResponse = DoneResponse()
        doneResponse.done = true

        return doneResponse
    }
}