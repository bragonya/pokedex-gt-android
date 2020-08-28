package com.bragonya.daggerdemo.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Evolution (
    @Json(name = "evolves_to")
    val evolvesTo: List<Evolution>,
    val species: PokeData
)