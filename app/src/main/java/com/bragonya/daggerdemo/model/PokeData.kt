package com.bragonya.daggerdemo.model

import android.os.Parcelable
import com.bragonya.daggerdemo.utils.BASE_IMAGE_URL
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PokeData (
    val name: String,
    val url: String
) : Parcelable {
    val pokeNumber get() = url.split("/").last { it.isNotEmpty() }.toInt()
    val imageURL get() = "${BASE_IMAGE_URL}${String.format("%03d", pokeNumber)}.png"
}