package com.example.backend.dto.response

import org.springframework.http.HttpStatus

class CommonException(var errorMessage: String, var status: HttpStatus) : Exception() {
    fun CommonException(errorMessage: String, status: HttpStatus) {
        this.errorMessage = errorMessage
        this.status = status
    }
}