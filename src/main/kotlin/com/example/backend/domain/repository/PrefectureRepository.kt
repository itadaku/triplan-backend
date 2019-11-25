package com.example.backend.domain.repository

import com.example.backend.domain.models.Prefecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PrefectureRepository : JpaRepository<Prefecture, Int> {
}
