package com.example.backend.domain.models.response

data class GetLinesResponse(
    var lines: List<LineResponse> = mutableListOf<LineResponse>()
)