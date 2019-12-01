package com.example.backend.controller

import com.example.backend.domain.models.Prefecture
import com.example.backend.domain.models.request.ProposeAreaBody
import com.example.backend.domain.models.response.ProposeAreaResponse
import com.example.backend.domain.models.util.PrefectureForSort
import com.example.backend.domain.service.impl.PrefectureServiceImpl
import com.example.backend.domain.service.impl.UserServiceImpl
import com.example.backend.dto.response.CommonException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.math.max

@RestController
class ProposeController {
    @Autowired
    lateinit var userServiceImpl : UserServiceImpl

    @Autowired
    lateinit var prefectureServiceImpl: PrefectureServiceImpl

    @GetMapping("api/v1/propose/1")
    fun proposeArea(@RequestParam token: String, @RequestBody body: ProposeAreaBody) : List<ProposeAreaResponse> {
        // TOKENの確認
        val findUser = userServiceImpl.findByToken(token)
        if(findUser.isEmpty()){
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        // それぞれの産業の最大値を取得
        val allPref = prefectureServiceImpl.findAll()
        var maxForestry = 0
        var maxFishingIndustry = 0
        var maxAgriculture = 0
        for (i in 0..46){
            maxForestry = max(maxForestry, allPref[i].forestry!!)
            maxFishingIndustry = max(maxFishingIndustry, allPref[i].fishingIndustry!!)
            maxAgriculture = max(maxAgriculture, allPref[i].agriculture!!)
        }

        // ソート用にデータを生成
        var res : List<ProposeAreaResponse> = mutableListOf()
        for (i in 0..46){
            val addItem = ProposeAreaResponse()

            addItem.id = allPref[i].id
            addItem.name = allPref[i].name
            addItem.population = allPref[i].population
            addItem.congestion_rate = 0.0f

            // match scoreを計算
            var sumValue  = 0.0
            var divValue  = 0.0
            if(body.agriculture!!) {
                sumValue += allPref[i].agriculture!!.toDouble() / maxAgriculture
                divValue += 1.0
            }
            if(body.forestry!!) {
                sumValue += allPref[i].forestry!!.toDouble() / maxForestry
                divValue += 1.0
            }
            if(body.fishing_industry!!) {
                sumValue += allPref[i].fishingIndustry!!.toDouble() / maxFishingIndustry
                divValue += 1.0
            }
            addItem.match_score = (sumValue / divValue * 100.0).toInt()

            res += addItem
        }

        return res
    }
}