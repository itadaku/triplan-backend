package com.example.backend.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.springframework.stereotype.Component

@Component
class Map {
    fun getRequiredTime(origin :String?, destination :String?): String {
        var reqUrl = "https://maps.googleapis.com/maps/api/directions/json"
        var apiKey = "API KEY"
       /*
        ("$reqUrl?origin=$origin&destination=$destination&key=$apiKey").httpGet().response { _, response, result ->
            when (result) {
                is Result.Success -> {
                    var resJSON = String(response.data)
                    // ここにJSONパース処理
                }
                is Result.Failure -> {
                    println("通信に失敗しました。")
                }
            }
        }
        */

        //本来ならここにJSONから取得した所要時間
        return "2時間30分"
    }
}
