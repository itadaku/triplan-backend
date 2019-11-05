package com.example.backend.controller

import com.example.backend.dto.response.CommonException
import com.example.backend.dto.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(CommonException::class)
    fun commonException(req: HttpServletRequest, error: CommonException): ResponseEntity<ErrorResponse> {
        return ErrorResponse.createCommonResponse(error)
    }

}