package com.example.backend.domain.repository

import com.example.backend.domain.models.Feedback
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedbackRepository : JpaRepository<Feedback, Int>{
}