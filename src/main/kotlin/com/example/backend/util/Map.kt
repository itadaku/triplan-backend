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
        var apiKey = "AIzaSyDaWBYhWzeIYQyoi7qQqOUNPSsu85EyVmw"
        println("$reqUrl?origin=$origin&destination=$destination&key=$apiKey")

        ("$reqUrl?origin=$origin&destination=$destination&key=$apiKey").httpGet().response { _, response, result ->
            when (result) {
                is Result.Success -> {
                    var resJSON = String(response.data)
                    /*ここにJSONパース処理

                    */
                    println(resJSON)
                }
                is Result.Failure -> {
                    println("通信に失敗しました。")
                }
            }
        }

        return "本来ならここに所要時間"
    }
}
