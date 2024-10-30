package com.pbws.data.model.quranmodel

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class QuranDto(

	@field:SerializedName("suwar")
	val suwar: List<SuwarItemDto?>? = null
)