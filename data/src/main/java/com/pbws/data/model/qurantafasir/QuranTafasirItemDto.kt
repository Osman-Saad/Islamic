package com.pbws.data.model.qurantafasir

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class QuranTafasirItemDto(
	val sura: String? = null,
	val aya: String? = null,
	val translation: String? = null,
	val arabic_text: String? = null,
)