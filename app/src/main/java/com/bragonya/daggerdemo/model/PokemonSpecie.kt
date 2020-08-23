package com.bragonya.daggerdemo.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpecie (
    val color: PokemonColor,
    @Json(name = "egg_groups")
    val eggGroups: List<Map<String, String>>,
    @Json(name = "evolution_chain")
    val evolutionChanURL: EvolutionChainURL

)
