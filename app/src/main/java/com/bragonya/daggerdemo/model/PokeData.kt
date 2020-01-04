package com.bragonya.daggerdemo.model

import com.bragonya.daggerdemo.utils.BASE_IMAGE_URL
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeData (
    val name: String,
    val url: String
)
{
    val pokeNumber get() = url.split("/").last { !it.isEmpty() }.toInt()
    val imageURL get() = "${BASE_IMAGE_URL}${String.format("%03d", pokeNumber)}.png"
}