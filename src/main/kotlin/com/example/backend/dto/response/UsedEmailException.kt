package com.example.backend.dto.response

class UsedEmailException(var errorMessage: String) : Exception() {
    fun UsedEmailException(errorMessage: String) {
        this.errorMessage = errorMessage
    }
}