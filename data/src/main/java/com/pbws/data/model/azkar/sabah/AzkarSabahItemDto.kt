package com.pbws.data.model.azkar.sabah

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AzkarSabahItemDto(

	@field:SerializedName("zekr")
	val zekr: String? = null,

)