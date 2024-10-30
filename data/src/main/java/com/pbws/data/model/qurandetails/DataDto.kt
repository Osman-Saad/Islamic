package com.pbws.data.model.qurandetails

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DataDto(
	val number: Int? = null,
	val englishName: String? = null,
	val numberOfAyahs: Int? = null,
	val name: String? = null,
	val ayahs: List<AyahsItemDto?>? = null,
)