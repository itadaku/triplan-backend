package com.example.backend.controller

import com.example.backend.domain.models.response.GetLinesResponse
import com.example.backend.domain.models.response.GetStationsResponse
import com.example.backend.domain.models.response.LineItem
import com.example.backend.domain.models.response.StationItem
import com.example.backend.domain.service.impl.LineServiceImpl
import com.example.backend.domain.service.impl.StationServiceImpl
import com.example.backend.domain.service.impl.LineStationServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LineController {
    @Autowired
    lateinit var lineServiceImpl: LineServiceImpl
    @Autowired
    lateinit var stationServiceImpl: StationServiceImpl
    @Autowired
    lateinit var lineStationServiceImpl: LineStationServiceImpl

    @GetMapping("api/v1/lines")
    fun getLines() : GetLinesResponse {
        val res = GetLinesResponse()
        var lines = lineServiceImpl.findAll()
        for(line in lines) {
            val item = LineItem()
            item.id = line.id
            item.name = line.name
            res.lines += item
        }
        
        return res
    }

    @GetMapping("api/v1/line/{line_id}/stations")
    fun getStations(@PathVariable("line_id") line_id: Int): GetStationsResponse {
        val res = GetStationsResponse()
        var stationsId = lineStationServiceImpl.findByLineId(line_id)
        for(stationId in stationsId){
            var stations = stationServiceImpl.findById(stationId.stationId)
            for(station in stations){
                val item = StationItem()
                item.id = station.id
                item.name = station.name
                res.stations += item
             }
        }

        return res
    }
}