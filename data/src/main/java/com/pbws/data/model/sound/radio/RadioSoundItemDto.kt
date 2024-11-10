package com.pbws.data.model.sound.radio

import kotlinx.serialization.Serializable

@Serializable
data class RadioSoundItemDto(
	val name: String? = null,
	val id: Int? = null,
	val url: String? = null
)