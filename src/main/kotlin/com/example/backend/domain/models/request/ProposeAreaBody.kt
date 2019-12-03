package com.example.backend.domain.models.request

import java.util.*

data class ProposeAreaBody (
    var agriculture : Boolean? = null,
    var forestry : Boolean? = null,
    var fishing_industry : Boolean? = null,
    var budget : Int? = null,
    var from_date : Date? = null,
    var to_date : Date? = null
)