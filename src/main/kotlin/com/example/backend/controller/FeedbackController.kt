package com.example.backend.controller

import com.example.backend.domain.models.Feedback
import com.example.backend.domain.models.response.DoneResponse
import com.example.backend.domain.service.impl.FeedbackServiceImpl
import com.example.backend.domain.service.impl.UserServiceImpl
import com.example.backend.dto.response.CommonException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class FeedbackController {
    @Autowired
    lateinit var userServiceImpl : UserServiceImpl

    @Autowired
    lateinit var feedbackServiceImpl: FeedbackServiceImpl

    @PostMapping("api/v1/app/feedback")
    fun sendFeedback(@RequestParam token: String, @RequestBody feedback: Feedback) : DoneResponse {
        val findUser = userServiceImpl.findByToken(token)
        if(findUser.isEmpty()) {
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        feedback.createdAt = Date()
        feedback.updateAt = Date()
        feedback.userId = findUser[0].id

        feedbackServiceImpl.save(feedback)

        var doneResponse = DoneResponse()
        doneResponse.done = true

        return doneResponse
    }
}