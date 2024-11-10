package com.pbws.domain.entity.soundentity.radio

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RadioSound(
	val radios: List<RadioSoundItem?>? = null
):Parcelable