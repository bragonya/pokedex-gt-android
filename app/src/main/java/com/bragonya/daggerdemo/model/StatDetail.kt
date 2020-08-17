package com.bragonya.daggerdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatDetail (
    val name: String
)