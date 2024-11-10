package com.pbws.data.model.praytime

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class DataDto(
	val date: DateDto? = null,
    val timings: TimingsDto? = null
)