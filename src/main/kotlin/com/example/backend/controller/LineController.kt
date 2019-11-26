package com.example.backend.controller

import com.example.backend.domain.models.response.GetLinesResponse
import com.example.backend.domain.models.response.GetStationsResponse
import com.example.backend.domain.models.response.LineItem
import com.example.backend.domain.models.response.StationItem
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LineController {
    @GetMapping("api/v1/lines")
    fun getLines() : GetLinesResponse {
        val res = GetLinesResponse()

        // 1つ目
        val item1 = LineItem()
        item1.id = 1
        item1.name = "山手線"
        res.lines += item1

        // 2つ目
        val item2 = LineItem()
        item2.id = 2
        item2.name = "総武線"
        res.lines += item2

        // 3つ目
        val item3 = LineItem()
        item3.id = 3
        item3.name = "中央線"
        res.lines += item3

        return res
    }

    @GetMapping("api/v1/line/{line_id}/stations")
    fun getStations(@PathVariable("line_id") line_id: Int): GetStationsResponse {
        val res = GetStationsResponse()

        if(line_id == 1){
            val item1 = StationItem()
            item1.id = 1
            item1.name = "新宿"
            res.stations += item1
            val item2 = StationItem()
            item2.id = 2
            item2.name = "代々木"
            res.stations += item2
            val item3 = StationItem()
            item3.id = 3
            item3.name = "原宿"
            res.stations += item3
        }else if(line_id == 2){
            val item4 = StationItem()
            item4.id = 4
            item4.name = "新宿"
            res.stations += item4
            val item5 = StationItem()
            item5.id = 5
            item5.name = "新小岩"
            res.stations += item5
            val item6 = StationItem()
            item6.id = 6
            item6.name = "平井"
            res.stations += item6
        }

        return res
    }
}