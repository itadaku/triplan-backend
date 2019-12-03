package com.example.backend.controller

import com.example.backend.domain.models.Element
import com.example.backend.domain.models.request.ProposeAreaBody
import com.example.backend.domain.models.request.ProposePlanBody
import com.example.backend.domain.models.response.PlanTag
import com.example.backend.domain.models.response.ProposeAreaResponse
import com.example.backend.domain.models.util.OnsenData
import com.example.backend.domain.service.impl.ElementServiceImpl
import com.example.backend.domain.service.impl.PrefectureServiceImpl
import com.example.backend.domain.service.impl.UserServiceImpl
import com.example.backend.dto.response.CommonException
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONArray
import org.json.JSONObject
import org.json.XML
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

@RestController
class ProposeController {
    @Autowired
    lateinit var userServiceImpl : UserServiceImpl

    @Autowired
    lateinit var prefectureServiceImpl: PrefectureServiceImpl

    @Autowired
    lateinit var planElementServiceImpl : ElementServiceImpl

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

    // 都道府県リストを取得してpref_codeを取得
    fun searchPrefectureCode(prefName: String): String {
        var prefCode = ""
        val areaSearchUrl = "http://jws.jalan.net/APICommon/AreaSearch/V1/"
        val areaSearchParamList: HashMap<String, String> = hashMapOf("key" to "vir16ec73edea4")
        val (_,response,resultAreaSearch) = areaSearchUrl.httpGet(areaSearchParamList.toList()).response()
        when(resultAreaSearch) {
            is Result.Success -> {
                val json = XML.toJSONObject(String(response.data))
                val jsonResults = json.get("Results") as JSONObject
                val jsonArea = jsonResults.get("Area") as JSONObject
                val jsonRegion = jsonArea.get("Region") as JSONArray
                for(nowRegion in jsonRegion) {
                    nowRegion as JSONObject

                    if(nowRegion.get("name").toString() == "北海道" || nowRegion.get("name").toString() == "沖縄") {
                        // 北海道と沖縄だけ例外処理
                        val jsonPrefecture = nowRegion.get("Prefecture") as JSONObject
                        if(jsonPrefecture.get("name").toString() == prefName){
                            prefCode = jsonPrefecture.get("cd").toString()
                        }
                    }else{
                        // その他都道府県
                        val jsonPrefecture = nowRegion.get("Prefecture") as JSONArray
                        for(nowPrefecture in jsonPrefecture) {
                            nowPrefecture as JSONObject
                            if(nowPrefecture.get("name").toString() == prefName){
                                prefCode = nowPrefecture.get("cd").toString()
                            }
                        }
                    }
                }
            }
        }

        return prefCode
    }

    // 温泉の施設を複数取得
    fun searchOnsen(prefCode: String) : List<OnsenData> {
        var res : List<OnsenData> = mutableListOf()
        // 温泉データを取得
        val onsenSearchUrl = "http://jws.jalan.net/APICommon/OnsenSearch/V1/"
        val onsenSearchParamList: HashMap<String, String> = hashMapOf("key" to "vir16ec73edea4", "pref" to prefCode)
        val (_,response,resultOnsenSearch) = onsenSearchUrl.httpGet(onsenSearchParamList.toList()).response()
        when(resultOnsenSearch) {
            is Result.Success -> {
                val json = XML.toJSONObject(String(response.data))
                val resultsJson = json.get("Results") as JSONObject
                val onsenJson = resultsJson.get("Onsen") as JSONArray
                for(nowOnsenJson in onsenJson) {
                    nowOnsenJson as JSONObject
                    var nowOnsenData = OnsenData()
                    nowOnsenData.name = nowOnsenJson.get("OnsenName").toString()
                    nowOnsenData.address = nowOnsenJson.get("OnsenAddress").toString()
                    res += nowOnsenData
                }
            }
        }
        return res
    }

    @GetMapping("api/v1/propose/2")
    fun proposePlan(@RequestParam token: String, @RequestBody body: ProposePlanBody) : String{
        // TOKENの確認
        val findUser = userServiceImpl.findByToken(token)
        if(findUser.isEmpty()){
            throw CommonException("Invalid Token", HttpStatus.BAD_REQUEST)
        }

        // 都道府県の名前を取得して、じゃらんの都道府県コード取得
        val searchPref = prefectureServiceImpl.findById(body.prefecture_id!!)
        val prefCode = searchPrefectureCode(searchPref.name!!)

        // タグから施設を取得(今は温泉のみ)
        for(nowTag in body.plan_tags){
            if(nowTag == 1){
                // pref_codeを利用して温泉を検索
                val onsenList = searchOnsen(prefCode)
                for(nowOnsen in onsenList){
                    // プランの要素テーブルに存在しなかったら追加
                    val searchResult = planElementServiceImpl.findByName(nowOnsen.name!!)
                    if(searchResult.isEmpty()){
                        var saveElement = Element()
                        saveElement.name = nowOnsen.name
                        saveElement.linkUrl = "none"
                        saveElement.body = nowOnsen.address
                        saveElement.createdAt = Date()
                        saveElement.updateAt = Date()
                        planElementServiceImpl.save(saveElement)
                    }
                }
            }
        }

        return "Success"
    }

    @GetMapping("api/v1/propose/tags")
    fun getTags() : List<PlanTag> {
        var res: List<PlanTag> = mutableListOf()

        var onsenTag = PlanTag()
        onsenTag.id = 1
        onsenTag.name = "温泉"

        res += onsenTag

        return res
    }
}