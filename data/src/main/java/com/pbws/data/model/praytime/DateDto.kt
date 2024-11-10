package com.pbws.data.model.praytime

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DateDto(
    val hijri: HijriDto? = null,
    val gregorian: GregorianDto? = null,
)