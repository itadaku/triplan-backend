package com.example.backend.domain.models.response

data class GetLinesResponse(
    var lines: List<LineItem> = mutableListOf<LineItem>()
)