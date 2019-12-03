package com.example.backend.controller

import com.example.backend.domain.models.PlanUser
import com.example.backend.domain.models.response.*
import com.example.backend.domain.models.typeEnum.ImageType
import com.example.backend.domain.models.typeEnum.PlanType
import com.example.backend.domain.service.impl.*
import com.example.backend.dto.response.CommonException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class PlanController {
    @Autowired
    lateinit var planServiceImpl : PlanServiceImpl

    @Autowired
    lateinit var elementServiceImpl : ElementServiceImpl

    @Autowired
    lateinit var planElementServiceImpl : PlanElementServiceImpl

    @Autowired
    lateinit var planLocationServiceImpl : PlanLocationServiceImpl

    @Autowired
    lateinit var prefectureServiceImpl : PrefectureServiceImpl

    @Autowired
    lateinit var userServiceImpl : UserServiceImpl

    @Autowired
    lateinit var planUserServiceImpl : PlanUserServiceImpl

    @GetMapping("api/v1/plan/top")
    fun getTopPlan(): List<TopPlanResponse> {
        val res = mutableListOf<TopPlanResponse>()

        // おすすめ
        val topPlanRes1 = TopPlanResponse()
        topPlanRes1.top_title = "おすすめ"
        // 1つ目
        val sampleTopPlan1 = TopPlanItem()
        sampleTopPlan1.id = 100000
        sampleTopPlan1.title = "東北遊覧船ツアー"
        sampleTopPlan1.image = "ship.jpg"
        sampleTopPlan1.review = 3.5
        sampleTopPlan1.days_nights = 2
        sampleTopPlan1.min_budget = 70000
        sampleTopPlan1.max_budget = 80000
        sampleTopPlan1.number_of_people = 2
        sampleTopPlan1.purpose += "海"
        sampleTopPlan1.purpose += "国内"
        topPlanRes1.plans += sampleTopPlan1

        // 2つ目
        val sampleTopPlan2 = TopPlanItem()
        sampleTopPlan2.id = 100001
        sampleTopPlan2.title = "日の出キャンプ"
        sampleTopPlan2.image = "camp.jpg"
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
        sampleTopPlan3.id = 1000002
        sampleTopPlan3.title = "国内スキー"
        sampleTopPlan3.image = "ski.jpg"
        sampleTopPlan3.review = 2.0
        sampleTopPlan3.days_nights = 1
        sampleTopPlan3.min_budget = 40000
        sampleTopPlan3.max_budget = 50000
        sampleTopPlan3.number_of_people = 1
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

        if(id == 100000 || id == 100001 || id == 100002){
            val sampleTopPlan1 = PlanItem()
            sampleTopPlan1.id = 100000
            sampleTopPlan1.title = "東北遊覧船ツアー"
            sampleTopPlan1.image = "ship.jpg"
            sampleTopPlan1.review = 3.5
            sampleTopPlan1.days_nights = 2
            sampleTopPlan1.min_budget = 70000
            sampleTopPlan1.max_budget = 80000
            sampleTopPlan1.number_of_people = 2
            sampleTopPlan1.purpose += "海"
            sampleTopPlan1.purpose += "国内"
            sampleTopPlan1.address = "宮城県"
            res.plan = sampleTopPlan1

            val planInfo1 = PlanInfoItem()
            planInfo1.id = 1
            planInfo1.title = "移動"
            planInfo1.body = "電車"
            planInfo1.start_time = Date(1557277200L*1000)
            planInfo1.end_time = Date(1557288000L*1000)
            planInfo1.image_path = "train.jpg"
            planInfo1.type = PlanType.MOVE.id
            planInfo1.days = 1
            res.schedules += planInfo1

            val planInfo2 = PlanInfoItem()
            planInfo2.id = 2
            planInfo2.title = "移動"
            planInfo2.body = "車"
            planInfo2.start_time = Date(1557288000L*1000)
            planInfo2.end_time = Date(1557291600L*1000)
            planInfo2.image_path = "car.jpg"
            planInfo2.type = PlanType.MOVE.id
            planInfo2.days = 1
            res.schedules += planInfo2

            val planInfo3 = PlanInfoItem()
            planInfo3.id = 3
            planInfo3.title = "昼食"
            planInfo3.body = "海の家で昼食"
            planInfo3.start_time = Date(1557291600L*1000)
            planInfo3.end_time = Date(1557295200L*1000)
            planInfo3.image_path = "food.jpg"
            planInfo3.type = PlanType.SPOT.id
            planInfo3.days = 1
            res.schedules += planInfo3

            val planInfo4 = PlanInfoItem()
            planInfo4.id = 4
            planInfo4.title = "移動"
            planInfo4.body = "車"
            planInfo4.start_time = Date(1557295200L*1000)
            planInfo4.end_time = Date(1557309600L*1000)
            planInfo4.image_path = "car.jpg"
            planInfo4.type = PlanType.MOVE.id
            planInfo4.days = 1
            res.schedules += planInfo4

            val planInfo5 = PlanInfoItem()
            planInfo5.id = 5
            planInfo5.title = "宿"
            planInfo5.body = "宿泊"
            planInfo5.start_time = Date(1557309600L*1000)
            planInfo5.end_time = Date(1557367200L*1000)
            planInfo5.image_path = "hotel.jpg"
            planInfo5.type = PlanType.SPOT.id
            planInfo5.days = 1
            res.schedules += planInfo5

            val planInfo6 = PlanInfoItem()
            planInfo6.id = 6
            planInfo6.title = "移動"
            planInfo6.body = "車"
            planInfo6.start_time = Date(1557367200L*1000)
            planInfo6.end_time = Date(1557378000L*1000)
            planInfo6.image_path = "car.jpg"
            planInfo6.type = PlanType.MOVE.id
            planInfo6.days = 2
            res.schedules += planInfo6

            val planInfo7 = PlanInfoItem()
            planInfo7.id = 7
            planInfo7.title = "船"
            planInfo7.body = "乗船"
            planInfo7.start_time = Date(1557378000L*1000)
            planInfo7.end_time = Date(1557396000L*1000)
            planInfo7.image_path = "ship.jpg"
            planInfo7.type = PlanType.SPOT.id
            planInfo7.days = 2
            res.schedules += planInfo7

            val planInfo8 = PlanInfoItem()
            planInfo8.id = 8
            planInfo8.title = "移動"
            planInfo8.body = "帰宅"
            planInfo8.start_time = Date(1557396000L*1000)
            planInfo8.end_time = Date(1557406800L*1000)
            planInfo8.image_path = "train.jpg"
            planInfo8.type = PlanType.MOVE.id
            planInfo8.days = 2
            res.schedules += planInfo8
        }else{
            val searchedPlan = planServiceImpl.findById(id).get()

            // プランの概要
            val topPlan = PlanItem()
            topPlan.id = searchedPlan.id
            topPlan.title = searchedPlan.title
            topPlan.days_nights = searchedPlan.daysNights
            topPlan.min_budget = searchedPlan.minBudget
            topPlan.max_budget = searchedPlan.maxBudget
            topPlan.number_of_people = searchedPlan.numberOfPeople
            topPlan.image = "onsen.jpg"
            topPlan.review = 0.0
            topPlan.purpose += "温泉"
            res.plan = topPlan

            var planLocationList = planLocationServiceImpl.findByPlanId(id)
            var prefectureId = planLocationList[0].locationId
            topPlan.address = prefectureServiceImpl.findById(prefectureId!!).name

            // プランの工程
            val planElementList = planElementServiceImpl.findByPlanId(id)
            for(nowPlanElement in planElementList) {
                val nowElement = elementServiceImpl.findById(nowPlanElement.element_id!!).get()

                val nowPlanInfo = PlanInfoItem()
                nowPlanInfo.id = nowPlanElement.id
                nowPlanInfo.title = nowElement.name
                nowPlanInfo.body = nowElement.body
                nowPlanInfo.image_path = "onsen.jpg"
                nowPlanInfo.start_time = nowPlanElement.from_date
                nowPlanInfo.end_time = nowPlanElement.to_date
                nowPlanInfo.type = PlanType.SPOT.id
                nowPlanInfo.days = 1
                if(nowPlanInfo.title == "移動"){
                    nowPlanInfo.type = PlanType.MOVE.id
                    nowPlanInfo.image_path = "train.jpg"
                }

                res.schedules += nowPlanInfo
            }
        }

        return res
    }

    @GetMapping("/api/v1/plan/detail")
    fun getPlanInfoDetail(@RequestParam id: Int): PlanDetailResponse {
        val nowElement = elementServiceImpl.findById(id)

        var res = PlanDetailResponse()
        res.id = id
        res.title = "観光"
        res.body = "美術館"
        res.review = 3.7
        res.link = "https://www.google.com/"

        if(nowElement.isPresent) {
            res.title = nowElement.get().name
            res.body = nowElement.get().body
            res.link = nowElement.get().linkUrl
        }

        var imagePath1 = ImageClass()
        imagePath1.image_path = "onsen.jpg"
        imagePath1.image_type = ImageType.MAIN.id
        var imagePath2 = ImageClass()
        imagePath2.image_path = "hotel.jpg"
        imagePath2.image_type = ImageType.SUB.id
        res.image_paths += imagePath1
        res.image_paths += imagePath2

        var reviewClass1 = ReviewClass()
        reviewClass1.body = "おもろかった"
        reviewClass1.icon = "user_icon.jpg"
        reviewClass1.sentence = "美術館がきれいで面白かった"
        reviewClass1.evaluation = 4.0
        var reviewClass2 = ReviewClass()
        reviewClass2.body = "微妙"
        reviewClass2.icon = "user_icon.jpg"
        reviewClass2.sentence = "汚かった"
        reviewClass2.evaluation = 3.0
        res.user_reviews += reviewClass1
        res.user_reviews += reviewClass2

        res.address = "東京都千代田区千代田1-1"

        return res
    }

    @PostMapping("api/v1/plan/now")
    fun postNowPlan(@RequestParam token: String, @RequestParam plan_id: Int) : String{
        val findUser = userServiceImpl.findByToken(token)
        if(findUser.isEmpty()){
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        // すでに紐付いてたら削除
        val findPlanUserList = planUserServiceImpl.findByUserId(findUser[0].id!!)
        if(findPlanUserList.isNotEmpty()){
            for(nowPlanUser in findPlanUserList){
                planUserServiceImpl.deleteById(nowPlanUser.id!!)
            }
        }

        // 新しく紐付ける
        var planUser = PlanUser()
        planUser.planId = plan_id
        planUser.userId = findUser[0].id
        planUser.isProgress = false
        planUser.nowProgressPlanElementId = 0
        planUser.createdAt = Date()
        planUser.updateAt = Date()
        planUserServiceImpl.save(planUser)

        return "Success"
    }

    @GetMapping("api/v1/plan/now")
    fun getNowPlan(@RequestParam token: String) : PlanInfoResponse {
        var res = NowResponse()

        val findUser = userServiceImpl.findByToken(token)
        if(findUser.isEmpty()){
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        val findPlanUserList = planUserServiceImpl.findByUserId(findUser[0].id!!)
        if(findPlanUserList.isEmpty()){
            throw CommonException("No Now Plan", HttpStatus.BAD_REQUEST)
        }
        res.plan_id = findPlanUserList[0].planId

        return getPlanInfo(res.plan_id!!)
    }

    @DeleteMapping("api/v1/plan/now")
    fun deleteNowPlan(@RequestParam token: String) : String {
        val findUser = userServiceImpl.findByToken(token)
        if(findUser.isEmpty()){
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        // すでに紐付いてたら削除
        val findPlanUserList = planUserServiceImpl.findByUserId(findUser[0].id!!)
        if(findPlanUserList.isNotEmpty()){
            for(nowPlanUser in findPlanUserList){
                planUserServiceImpl.deleteById(nowPlanUser.id!!)
            }
        }

        return "Success"
    }
}