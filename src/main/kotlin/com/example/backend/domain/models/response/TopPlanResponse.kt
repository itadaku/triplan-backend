package com.example.backend.domain.models.response

import javax.persistence.Entity
import javax.persistence.Id

data class TopPlanResponse (
    var top_title: String? = null,
    var plans: List<TopPlanItem> = mutableListOf()
)