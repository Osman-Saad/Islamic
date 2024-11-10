package com.pbws.data.model.azkar.sabah

import kotlinx.serialization.Serializable

@Serializable
data class AzkarSabahDto(

	val title: String? = null,

	val content: List<AzkarSabahItemDto?>? = null
)