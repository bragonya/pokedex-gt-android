package com.bragonya.daggerdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeRoot (
    val next: String,
    val previous: String?,
    val results: List<PokeData>
)