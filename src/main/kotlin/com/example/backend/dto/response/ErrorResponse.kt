package com.example.backend.dto.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponse(var message: String) {
    companion object {
        fun createCommonResponse(e: CommonException) : ResponseEntity<ErrorResponse> {
            return ResponseEntity<ErrorResponse>(ErrorResponse(e.errorMessage), e.status)
        }
    }
}