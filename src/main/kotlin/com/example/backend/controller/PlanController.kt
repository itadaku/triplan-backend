package com.example.backend.controller

import com.example.backend.domain.models.TopPlan
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlanController {
    @GetMapping("api/v1/plan/top")
    fun getTopPlan(): List<TopPlan> {
        val res = mutableListOf<TopPlan>()

        // 1つ目
        val sampleTopPlan1 = TopPlan()
        sampleTopPlan1.id = 1
        sampleTopPlan1.title = "Sample Plan 1"
        sampleTopPlan1.image = "none"
        sampleTopPlan1.review = 3.5
        sampleTopPlan1.days_nights = 3
        sampleTopPlan1.min_budget = 25000
        sampleTopPlan1.max_budget = 30000
        sampleTopPlan1.number_of_people = 2
        sampleTopPlan1.purpose += "海"
        sampleTopPlan1.purpose += "国内"
        res += sampleTopPlan1

        // 2つ目
        val sampleTopPlan2 = TopPlan()
        sampleTopPlan2.id = 2
        sampleTopPlan2.title = "Sample Plan 2"
        sampleTopPlan2.image = "none"
        sampleTopPlan2.review = 4.1
        sampleTopPlan2.days_nights = 2
        sampleTopPlan2.min_budget = 200000
        sampleTopPlan2.max_budget = 250000
        sampleTopPlan2.number_of_people = 2
        sampleTopPlan2.purpose += "山"
        sampleTopPlan2.purpose += "キャンプ"
        sampleTopPlan2.purpose += "海外"
        res += sampleTopPlan2

        // 3つ目
        val sampleTopPlan3 = TopPlan()
        sampleTopPlan3.id = 3
        sampleTopPlan3.title = "Sample Plan 3"
        sampleTopPlan3.image = "none"
        sampleTopPlan3.review = 2.0
        sampleTopPlan3.days_nights = 1
        sampleTopPlan3.min_budget = 40000
        sampleTopPlan3.max_budget = 50000
        sampleTopPlan3.number_of_people = 3
        sampleTopPlan3.purpose += "スキー"
        sampleTopPlan3.purpose += "冬"
        sampleTopPlan3.purpose += "国内"
        res += sampleTopPlan3

        return res
    }
}