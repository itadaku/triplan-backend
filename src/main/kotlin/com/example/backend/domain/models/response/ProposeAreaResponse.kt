package com.example.backend.domain.models.response

data class ProposeAreaResponse (
    var id : Int? = null,
    var name : String? = null,
    var match_score : Int? = null,
    var population : String? = null,
    var congestion_rate : Float? = null
)