package com.example.backend.controller

import com.example.backend.domain.models.PlanInfoElement
import com.example.backend.domain.models.PlanType
import com.example.backend.domain.models.TopPlan
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

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

    @GetMapping("api/v1/plan/info")
    fun getPlanInfo(@RequestParam id: Int) : List<PlanInfoElement> {
        val res = mutableListOf<PlanInfoElement>()

        if(id in 1..3){
            val planInfo1 = PlanInfoElement()
            planInfo1.id = 1
            planInfo1.title = "移動"
            planInfo1.body = "車"
            planInfo1.start_time = Date(1557277200L*1000)
            planInfo1.end_time = Date(1557280800L*1000)
            planInfo1.image_path = "none"
            planInfo1.type = PlanType.MOVE.id
            res += planInfo1

            val planInfo2 = PlanInfoElement()
            planInfo2.id = 2
            planInfo2.title = "海"
            planInfo2.body = "海"
            planInfo2.start_time = Date(1557280800L*1000)
            planInfo2.end_time = Date(1557284400L*1000)
            planInfo2.image_path = "none"
            planInfo2.type = PlanType.SPOT.id
            res += planInfo2

            val planInfo3 = PlanInfoElement()
            planInfo3.id = 3
            planInfo3.title = "昼食"
            planInfo3.body = "海の家で昼食"
            planInfo3.start_time = Date(1557284400L*1000)
            planInfo3.end_time = Date(1557288000L*1000)
            planInfo3.image_path = "none"
            planInfo3.type = PlanType.SPOT.id
            res += planInfo3

            val planInfo4 = PlanInfoElement()
            planInfo4.id = 4
            planInfo4.title = "移動"
            planInfo4.body = "車"
            planInfo4.start_time = Date(1557288000L*1000)
            planInfo4.end_time = Date(1557289800L*1000)
            planInfo4.image_path = "none"
            planInfo4.type = PlanType.MOVE.id
            res += planInfo4

            val planInfo5 = PlanInfoElement()
            planInfo5.id = 4
            planInfo5.title = "観光"
            planInfo5.body = "美術館"
            planInfo5.start_time = Date(1557289800L*1000)
            planInfo5.end_time = Date(1557298800L*1000)
            planInfo5.image_path = "none"
            planInfo5.type = PlanType.SPOT.id
            res += planInfo5

            val planInfo6 = PlanInfoElement()
            planInfo6.id = 4
            planInfo6.title = "移動"
            planInfo6.body = "帰宅"
            planInfo6.start_time = Date(1557298800L*1000)
            planInfo6.end_time = Date(1557302400L*1000)
            planInfo6.image_path = "none"
            planInfo6.type = PlanType.MOVE.id
            res += planInfo6
        }

        return res
    }
}