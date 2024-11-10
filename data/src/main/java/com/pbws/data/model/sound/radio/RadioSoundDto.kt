package com.pbws.data.model.sound.radio

import kotlinx.serialization.Serializable


@Serializable
data class RadioSoundDto(
	val radios: List<RadioSoundItemDto?>? = null
)