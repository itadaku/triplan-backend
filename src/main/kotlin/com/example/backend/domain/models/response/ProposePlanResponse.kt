package com.example.backend.domain.models.response

import com.example.backend.domain.models.Plan

data class ProposePlanResponse (
    var propose_plans : List<TopPlanItem> = listOf()
)