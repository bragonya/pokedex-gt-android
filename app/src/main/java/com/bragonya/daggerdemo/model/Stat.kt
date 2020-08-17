package com.bragonya.daggerdemo.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stat(
    @Json(name = "base_stat")
    val baseStat: Int,
    @Json(name = "stat")
    val statDetail: StatDetail

)