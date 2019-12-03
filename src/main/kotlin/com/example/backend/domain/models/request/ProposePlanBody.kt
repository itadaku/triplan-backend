package com.example.backend.domain.models.request

import com.example.backend.domain.models.response.PlanTag
import java.util.*

data class ProposePlanBody (
        var prefecture_id : Int? = null,
        var plan_tags : List<Int> = listOf(),
        var from_date : Date? = null,
        var to_date : Date? = null
)