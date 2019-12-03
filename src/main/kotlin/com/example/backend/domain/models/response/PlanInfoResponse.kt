package com.example.backend.domain.models.response

import java.util.*


data class PlanInfoResponse (
        var plan : PlanItem? = null,
        var schedules : List<PlanInfoItem> = listOf()
)