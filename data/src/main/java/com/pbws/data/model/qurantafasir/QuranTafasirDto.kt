package com.pbws.data.model.qurantafasir

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class QuranTafasirDto(
	val result: List<QuranTafasirItemDto?>? = null
)