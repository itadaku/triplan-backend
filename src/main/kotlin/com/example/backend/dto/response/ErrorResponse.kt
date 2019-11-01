package com.example.backend.dto.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponse(var message: String) {
    companion object {
        fun createResponse(e: AlreadyExistsException) : ResponseEntity<ErrorResponse> {
            return ResponseEntity<ErrorResponse>(ErrorResponse(e.errorMessage), HttpStatus.BAD_REQUEST)
        }

        fun createUsedEmailResponse(e: UsedEmailException) : ResponseEntity<ErrorResponse> {
            return ResponseEntity<ErrorResponse>(ErrorResponse(e.errorMessage), HttpStatus.BAD_REQUEST)
        }

        fun createUserIsNoneResponse(e: UserIsNoneException) : ResponseEntity<ErrorResponse> {
            return ResponseEntity<ErrorResponse>(ErrorResponse(e.errorMessage), HttpStatus.BAD_REQUEST)
        }
    }
}