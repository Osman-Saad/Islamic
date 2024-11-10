package com.pbws.data.model.praytime

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TimingsDto(

    @SerialName("Sunset")
    val sunset: String? = null,

    @SerialName("Asr")
    val asr: String? = null,

    @SerialName("Isha")
    val isha: String? = null,

    @SerialName("Fajr")
    val fajr: String? = null,

    @SerialName("Dhuhr")
    val dhuhr: String? = null,

    @SerialName("Maghrib")
    val maghrib: String? = null,

    @SerialName("Lastthird")
    val lastthird: String? = null,

    @SerialName("Firstthird")
    val firstthird: String? = null,

    @SerialName("Sunrise")
    val sunrise: String? = null,

    @SerialName("Midnight")
    val midnight: String? = null,

    @SerialName("Imsak")
    val imsak: String? = null
)