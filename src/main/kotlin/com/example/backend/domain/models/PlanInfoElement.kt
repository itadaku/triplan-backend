package com.example.backend.domain.models

import java.util.*


data class PlanInfoElement (
        var id: Int? = null,
        var title: String? = null,
        var body: String? = null,
        var start_time: Date? = null,
        var end_time: Date? = null,
        var image_path: String? = null,
        var type: Int? = null
)