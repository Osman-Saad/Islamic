package com.pbws.data.model.sound.quran

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuranSoundDto(

	@SerialName("audio_files")
	val audioFiles: List<QuranFilesItemDto?>? = null
)