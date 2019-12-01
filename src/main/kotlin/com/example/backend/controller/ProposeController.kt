package com.example.backend.controller

import com.example.backend.domain.models.request.ProposeAreaBody
import com.example.backend.domain.models.response.ProposeAreaResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProposeController {
    @GetMapping("api/v1/propose/1")
    fun proposeArea(@RequestParam token: String, @RequestBody body: ProposeAreaBody) : List<ProposeAreaResponse> {
        var res : List<ProposeAreaResponse> = mutableListOf()


        return res
    }
}