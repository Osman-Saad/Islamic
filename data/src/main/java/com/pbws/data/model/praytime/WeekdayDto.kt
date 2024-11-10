package com.pbws.data.model.praytime

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class WeekdayDto(
	val en: String? = null,
	val ar: String? = null
)