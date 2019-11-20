package com.example.backend.util

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.example.backend.domain.models.Station
import com.example.backend.domain.service.impl.StationServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import java.util.Date


data class StationLineModel(var line_cd:Int, var line_name: String, var line_lon:Float, var line_lat:Float, var line_zoom:Int, var station_l:Array<StationModel>)
data class StationModel(var station_cd:Int, var station_g_cd:Int, var station_name:String, var lon:Float, var lat:Float)
@Component
class GetStation {
    @Autowired
    lateinit var stationServiceImpl : StationServiceImpl

    @PostConstruct
    fun getData(){
        var reqBaseUrl = "http://www.ekidata.jp/api/l/"
        val reqJson: List<String> = listOf(

                "11101.json",
                "11102.json"
 /*
                "11103.json",
                "11104.json",
                "11105.json",
                "11106.json",
                "11107.json",
                "11108.json",
                "11109.json",
                "11110.json"
*/
        )
        for(reqJsonFile in reqJson) {
            (reqBaseUrl + reqJsonFile).httpGet().response { _, response, result ->
                when (result) {
                    is Result.Success -> {
                        var res = String(response.data)
                        var resArr = res.split("\n")
                        var json = resArr[2].removePrefix("xml.data = ")
                        val mapper = jacksonObjectMapper()
                        val stations = mapper.readValue<StationLineModel>(json)

                        println("---------- " + stations.line_name + " ----------")
                        for (stationA in stations.station_l) {
                            println(stationA.station_name)
                            var station = Station()
                            station.name= stationA.station_name
                            station.updatedAt = Date()
                            station.createdAt = Date()
                            stationServiceImpl.save(station)
                        }
                    }
                    is Result.Failure -> {
                        println("通信に失敗しました。")
                    }
                }
                Thread.sleep(2000L)
            }
        }
    }
}