package com.pbws.data.model.sound.quran

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuranFilesItemDto(
	val format: String? = null,

	@SerialName("audio_url")
	val audioUrl: String? = null,

	val id: Int? = null,

	@SerialName("chapter_id")
	val chapterId: Int? = null,
)