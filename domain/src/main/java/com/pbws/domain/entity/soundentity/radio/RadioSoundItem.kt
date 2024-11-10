package com.pbws.domain.entity.soundentity.radio

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RadioSoundItem(
	val name: String? = null,
	val id: Int? = null,
	val url: String? = null
):Parcelable