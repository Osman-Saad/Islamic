package com.pbws.data.model.praytime

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class HijriDto(

	val date: String? = null,
	val month: MonthDto? = null,
	val year: String? = null,
	val weekday: WeekdayDto? = null,
	val day: String? = null
)