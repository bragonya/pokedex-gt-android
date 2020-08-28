package com.bragonya.daggerdemo.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EvolutionChain (
    @Json(name = "evolves_to")
    val evolvesTo: List<Evolution>,
    @Json(name = "species")
    val species: PokeData
)
