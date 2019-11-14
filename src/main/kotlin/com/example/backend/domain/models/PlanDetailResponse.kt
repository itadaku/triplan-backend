package com.example.backend.domain.models


data class PlanDetailResponse (
    var id: Int? = null,
    var title: String? = null,
    var body: String? = null,
    var review: Double? = null,
    var link: String? = null,
    var image_paths: List<ImageClass> = listOf(),
    var user_reviews: List<ReviewClass> = listOf(),
    var address: String? = null
)