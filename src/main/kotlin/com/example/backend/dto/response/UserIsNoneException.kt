package com.example.backend.dto.response

class UserIsNoneException(var errorMessage: String) : Exception() {
    fun UserIsNoneException(errorMessage: String) {
        this.errorMessage = errorMessage
    }
}