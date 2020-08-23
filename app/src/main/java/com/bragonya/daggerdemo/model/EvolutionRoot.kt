package com.bragonya.daggerdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EvolutionRoot (
    val chain: EvolutionChain
)