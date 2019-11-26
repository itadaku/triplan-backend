package com.example.backend.controller

import com.example.backend.domain.models.response.GetLinesResponse
import com.example.backend.domain.models.response.LineResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LineController {
    @GetMapping("api/v1/lines")
    fun getLines() : GetLinesResponse {
        var res = GetLinesResponse()

        // 1つ目
        var item1 = LineResponse()
        item1.id = 1
        item1.name = "山手線"
        res.lines += item1

        // 2つ目
        var item2 = LineResponse()
        item2.id = 2
        item2.name = "総武線"
        res.lines += item2

        // 3つ目
        var item3 = LineResponse()
        item3.id = 3
        item3.name = "中央線"
        res.lines += item3

        return res
    }
}