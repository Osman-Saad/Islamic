package com.pbws.domain.entity.soundentity.quran

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class QuranSound(

	val audioFiles: List<QuranFilesItem?>? = null
):Parcelable