package com.example.backend.dto.response

class AlreadyExistsException(var errorMessage: String) : Exception() {
    fun AlreadyExistsException(errorMessage: String) {
        this.errorMessage = errorMessage
    }
}