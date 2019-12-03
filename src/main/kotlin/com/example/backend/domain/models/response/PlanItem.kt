package com.example.backend.domain.models.response

data class PlanItem (
    var id: Int? = null,
    var title: String? = null,
    var image: String? = null,
    var review: Double? = null,
    var days_nights: Int? = null,
    var min_budget: Int? = null,
    var max_budget: Int? = null,
    var number_of_people: Int? = null,
    var address: String? = null,
    var purpose: List<String> = listOf()
)