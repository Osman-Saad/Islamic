package com.pbws.data.model.ahadeth

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AhadethItemDto(
	val number: Int? = null,
	val arab: String? = null
)