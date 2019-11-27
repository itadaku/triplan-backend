package com.example.backend.domain.service.impl

import com.example.backend.domain.models.Feedback
import com.example.backend.domain.repository.FeedbackRepository
import com.example.backend.domain.service.FeedbackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeedbackServiceImpl : FeedbackService{
    @Autowired
    lateinit var feedbackRepository: FeedbackRepository

    fun save(feedback: Feedback) : Feedback {
        feedbackRepository.save(feedback)
        return feedback
    }
}