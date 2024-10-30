package com.pbws.data.model.qurandetails

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AyahsItemDto(
	val number: Int? = null,
	val text: String? = null,
	val numberInSurah: Int? = null,
)