package com.example.backend.controller

import com.example.backend.domain.models.response.*
import com.example.backend.domain.models.typeEnum.ImageType
import com.example.backend.domain.models.typeEnum.PlanType
import com.example.backend.domain.service.impl.ElementServiceImpl
import com.example.backend.domain.service.impl.PlanElementServiceImpl
import com.example.backend.domain.service.impl.PlanServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class PlanController {
    @Autowired
    lateinit var planServiceImpl : PlanServiceImpl

    @Autowired
    lateinit var elementServiceImpl : ElementServiceImpl

    @Autowired
    lateinit var planElementServiceImpl : PlanElementServiceImpl

    @GetMapping("api/v1/plan/top")
    fun getTopPlan(): List<TopPlanResponse> {
        val res = mutableListOf<TopPlanResponse>()

        // おすすめ
        val topPlanRes1 = TopPlanResponse()
        topPlanRes1.top_title = "おすすめ"
        // 1つ目
        val sampleTopPlan1 = TopPlanItem()
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
        topPlanRes1.plans += sampleTopPlan1

        // 2つ目
        val sampleTopPlan2 = TopPlanItem()
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
        topPlanRes1.plans += sampleTopPlan2

        // 他のユーザー
        val topPlanRes2 = TopPlanResponse()
        topPlanRes2.top_title = "他のユーザー"
        // 3つ目
        val sampleTopPlan3 = TopPlanItem()
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
        topPlanRes2.plans += sampleTopPlan3

        //
        res += topPlanRes1
        res += topPlanRes2

        return res
    }

    @GetMapping("api/v1/plan/info")
    fun getPlanInfo(@RequestParam id: Int) : PlanInfoResponse {
        val res = PlanInfoResponse()

        if(id == 0){
            val sampleTopPlan1 = TopPlanItem()
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
            res.plan = sampleTopPlan1

            val planInfo1 = PlanInfoItem()
            planInfo1.id = 1
            planInfo1.title = "移動"
            planInfo1.body = "車"
            planInfo1.start_time = Date(1557277200L*1000)
            planInfo1.end_time = Date(1557280800L*1000)
            planInfo1.image_path = "none"
            planInfo1.type = PlanType.MOVE.id
            res.schedules += planInfo1

            val planInfo2 = PlanInfoItem()
            planInfo2.id = 2
            planInfo2.title = "海"
            planInfo2.body = "海"
            planInfo2.start_time = Date(1557280800L*1000)
            planInfo2.end_time = Date(1557284400L*1000)
            planInfo2.image_path = "none"
            planInfo2.type = PlanType.SPOT.id
            res.schedules += planInfo2

            val planInfo3 = PlanInfoItem()
            planInfo3.id = 3
            planInfo3.title = "昼食"
            planInfo3.body = "海の家で昼食"
            planInfo3.start_time = Date(1557284400L*1000)
            planInfo3.end_time = Date(1557288000L*1000)
            planInfo3.image_path = "none"
            planInfo3.type = PlanType.SPOT.id
            res.schedules += planInfo3

            val planInfo4 = PlanInfoItem()
            planInfo4.id = 4
            planInfo4.title = "移動"
            planInfo4.body = "車"
            planInfo4.start_time = Date(1557288000L*1000)
            planInfo4.end_time = Date(1557289800L*1000)
            planInfo4.image_path = "none"
            planInfo4.type = PlanType.MOVE.id
            res.schedules += planInfo4

            val planInfo5 = PlanInfoItem()
            planInfo5.id = 5
            planInfo5.title = "観光"
            planInfo5.body = "美術館"
            planInfo5.start_time = Date(1557289800L*1000)
            planInfo5.end_time = Date(1557298800L*1000)
            planInfo5.image_path = "none"
            planInfo5.type = PlanType.SPOT.id
            res.schedules += planInfo5

            val planInfo6 = PlanInfoItem()
            planInfo6.id = 6
            planInfo6.title = "移動"
            planInfo6.body = "帰宅"
            planInfo6.start_time = Date(1557298800L*1000)
            planInfo6.end_time = Date(1557302400L*1000)
            planInfo6.image_path = "none"
            planInfo6.type = PlanType.MOVE.id
            res.schedules += planInfo6
        }else{
            val searchedPlan = planServiceImpl.findById(id).get()

            // プランの概要
            val topPlan = TopPlanItem()
            topPlan.id = searchedPlan.id
            topPlan.title = searchedPlan.title
            topPlan.days_nights = searchedPlan.daysNights
            topPlan.min_budget = searchedPlan.minBudget
            topPlan.max_budget = searchedPlan.maxBudget
            topPlan.number_of_people = searchedPlan.numberOfPeople
            topPlan.image = "none"
            topPlan.review = 0.0
            topPlan.purpose += "温泉"
            res.plan = topPlan

            // プランの工程
            val planElementList = planElementServiceImpl.findByPlanId(id)
            for(nowPlanElement in planElementList) {
                val nowElement = elementServiceImpl.findById(nowPlanElement.element_id!!).get()

                val nowPlanInfo = PlanInfoItem()
                nowPlanInfo.id = nowPlanElement.id
                nowPlanInfo.title = nowElement.name
                nowPlanInfo.body = nowElement.body
                nowPlanInfo.image_path = "none"
                nowPlanInfo.start_time = nowPlanElement.from_date
                nowPlanInfo.end_time = nowPlanElement.to_date
                nowPlanInfo.type = PlanType.SPOT.id
                if(nowPlanInfo.title == "移動"){
                    nowPlanInfo.type = PlanType.MOVE.id
                }

                res.schedules += nowPlanInfo
            }
        }

        return res
    }

    @GetMapping("/api/v1/plan/detail")
    fun getPlanInfoDetail(@RequestParam id: Int): PlanDetailResponse {
        var res = PlanDetailResponse()
        res.id = 1
        res.title = "観光"
        res.body = "美術館"
        res.review = 3.7
        res.link = "http://sample.com"

        var imagePath1 = ImageClass()
        imagePath1.image_path = "none"
        imagePath1.image_type = ImageType.MAIN.id
        var imagePath2 = ImageClass()
        imagePath2.image_path = "none"
        imagePath2.image_type = ImageType.SUB.id
        res.image_paths += imagePath1
        res.image_paths += imagePath2

        var reviewClass1 = ReviewClass()
        reviewClass1.body = "おもろかった"
        reviewClass1.icon = "none"
        reviewClass1.sentence = "美術館がきれいで面白かった"
        reviewClass1.evaluation = 4.0
        var reviewClass2 = ReviewClass()
        reviewClass2.body = "微妙"
        reviewClass2.icon = "none"
        reviewClass2.sentence = "汚かった"
        reviewClass2.evaluation = 3.0
        res.user_reviews += reviewClass1
        res.user_reviews += reviewClass2

        res.address = "東京都千代田区千代田1-1"

        return res
    }


}