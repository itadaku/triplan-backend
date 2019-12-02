package com.example.backend.util

import com.example.backend.domain.models.Prefecture
import com.example.backend.domain.service.impl.PrefectureServiceImpl
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.fuel.json.responseJson
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import kotlin.collections.HashMap

@Component
class GetPrefectureData {
    @Autowired
    lateinit var prefServiceImpl : PrefectureServiceImpl

    @PostConstruct
    fun getData() {
        println("Start get prefectures.")
        prefServiceImpl.deleteAll()
        val getPrefecturesUrl = "https://opendata.resas-portal.go.jp/api/v1/prefectures"    // 都道府県一覧
        val getAgricultureUrl = "https://opendata.resas-portal.go.jp/api/v1/agriculture/sales/forLine"  // 農業産出額
        val getForestryUrl = "https://opendata.resas-portal.go.jp/api/v1/forestry/income/forStacked"    // 林業総収入
        val getFishingIndustryUrl = "https://opendata.resas-portal.go.jp/api/v1/fishery/sea/totalSales"    // 海面漁獲物等販売金額
        val getPopulationUrl = "https://opendata.resas-portal.go.jp/api/v1/population/composition/perYear"  // 人口

        val header: HashMap<String, String> = hashMapOf("X-API-KEY" to "QBy66lc6tWvIg0Wj1QDxX7nD7HYKPEzbtb5256eS")

        // 都道府県データのリスト
        val prefList : Array<Prefecture?> = arrayOfNulls(47)
        val populationList : Array<Int?> = arrayOfNulls(47)

        // 都道府県一覧を取得
        val (_,_,resultGetPrefectures) = getPrefecturesUrl.httpGet().header(header).responseJson()
        when(resultGetPrefectures) {
            is Result.Success -> {
                val json = resultGetPrefectures.get().obj()
                val prefArray = json.get("result") as JSONArray
                for (nowPref in prefArray) {
                    nowPref as JSONObject
                    val nowPrefCode = nowPref.get("prefCode").toString().toInt()
                    prefList[nowPrefCode-1] = Prefecture()
                    prefList[nowPrefCode-1]?.name = nowPref.get("prefName").toString()
                }
            }
        }

        for (i in 0..46) {
            // 農業産出額を取得
            val paramList : HashMap<String, String> = hashMapOf("prefCode" to (i+1).toString(), "matter" to "2")
            val (_, _, getAgricultureResult) = getAgricultureUrl.httpGet(paramList.toList()).header(header).responseJson()
            when(getAgricultureResult) {
                is Result.Success -> {
                    val json = getAgricultureResult.get().obj()
                    val agriResult = json.get("result") as JSONObject
                    val agriResultData = agriResult.get("data") as JSONArray
                    for(nowAgri in agriResultData) {
                        nowAgri as JSONObject
                        if(nowAgri.get("year").toString() == "2016"){
                            prefList[i]?.agriculture = nowAgri.get("value").toString().toInt()
                        }
                    }
                }
            }
            // 林業の総収入を取得
            val forestryParamList : HashMap<String, String> = hashMapOf("prefCode" to (i+1).toString(), "cityCode" to "-")
            val (_, _, getForestryResult) = getForestryUrl.httpGet(forestryParamList.toList()).header(header).responseJson()
            when(getForestryResult){
                is Result.Success -> {
                    val json = getForestryResult.get().obj()
                    val foreResult = json.get("result") as JSONObject
                    val foreResultYears = foreResult.get("years") as JSONArray
                    for(nowFore in foreResultYears) {
                        nowFore as JSONObject
                        if(nowFore.get("year").toString() == "2015"){
                            val nowSales = nowFore.get("sales").toString().toInt()
                            val nowIncome = nowFore.get("income").toString().toInt()
                            prefList[i]?.forestry = nowSales + nowIncome
                        }
                    }
                }
            }
            // 海面漁獲物等販売金額を取得
            val fishingIndustryParamList : HashMap<String, String> = hashMapOf("prefCode" to (i+1).toString(), "cityCode" to "-")
            val (_, _, getFishingIndustryResult) = getFishingIndustryUrl.httpGet(fishingIndustryParamList.toList()).header(header).responseJson()
            when(getFishingIndustryResult) {
                is Result.Success -> {
                    val json = getFishingIndustryResult.get().obj()
                    if(json.get("result").toString() == "null"){
                        prefList[i]?.fishingIndustry = 0
                    }else {
                        val fishResult = json.get("result") as JSONObject
                        val fishResultYears = fishResult.get("years") as JSONArray
                        for (nowFish in fishResultYears) {
                            nowFish as JSONObject
                            if (nowFish.get("year").toString() == "2013") {
                                prefList[i]?.fishingIndustry = nowFish.get("value").toString().toInt()
                            }
                        }
                    }
                }
            }
            // 人口を取得
            val populationParamList : HashMap<String, String> = hashMapOf("prefCode" to (i+1).toString(), "cityCode" to "-")
            val (_, _, getPopulationResult) = getPopulationUrl.httpGet(populationParamList.toList()).header(header).responseJson()
            when(getPopulationResult) {
                is Result.Success -> {
                    val json = getPopulationResult.get().obj()
                    val populationResult = json.get("result") as JSONObject
                    val populationResultData = populationResult.get("data") as JSONArray
                    for (nowData in populationResultData) {
                        nowData as JSONObject
                        if(nowData.get("label") == "総人口"){
                            val nowDataData = nowData.get("data") as JSONArray
                            for(nowPopulationDataYear in nowDataData) {
                                nowPopulationDataYear as JSONObject
                                if(nowPopulationDataYear.get("year") == 2020){
                                    populationList[i] = nowPopulationDataYear.get("value") as Int?
                                }
                            }
                        }
                    }
                }
            }
            // 1秒スリープ
            Thread.sleep(1100L)
            println("GetPrefEnd[$i/46]")
        }

        // 人口を多い・普通・少ないの3種類に分けてデータを登録
        val beforeSortPopulationList : List<Int?> = populationList.toList()
        populationList.sort()
        for(i in 0..46) {
            if(beforeSortPopulationList[i]!! < populationList[15]!!){
                prefList[i]?.population = "少ない"
            }else if(beforeSortPopulationList[i]!! < populationList[30]!!){
                prefList[i]?.population = "普通"
            }else{
                prefList[i]?.population = "多い"
            }
        }

        for(i in 0..46){
            val saveData = Prefecture()
            saveData.id = i
            saveData.name = prefList[i]?.name
            saveData.agriculture = prefList[i]?.agriculture
            saveData.forestry = prefList[i]?.forestry
            saveData.fishingIndustry = prefList[i]?.fishingIndustry
            saveData.population = prefList[i]?.population
            saveData.createdAt = Date()
            saveData.updatedAt = Date()
            prefServiceImpl.save(saveData)
        }
    }
}