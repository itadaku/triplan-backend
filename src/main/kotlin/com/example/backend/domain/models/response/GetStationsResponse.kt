package com.example.backend.domain.models.response

data class GetStationsResponse (
    var stations: List<StationItem> = mutableListOf<StationItem>()
)