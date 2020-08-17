package com.bragonya.daggerdemo.model

import com.bragonya.daggerdemo.utils.BACK_IMAGE
import com.bragonya.daggerdemo.utils.FRONT_IMAGE
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetail (
    val name: String,
    val weight: Int,
    val height: Int,
    val stats: List<Stat>,
    val sprites: Map<String, Object>
){
    fun getStatValue(key: String) = stats.find { it.statDetail.name == key }?.baseStat ?: 0

    fun getFrontImage() = sprites[FRONT_IMAGE].toString()

    fun getBackImage() = sprites[BACK_IMAGE].toString()
}