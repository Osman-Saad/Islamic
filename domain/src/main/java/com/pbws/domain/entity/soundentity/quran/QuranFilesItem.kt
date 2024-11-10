package com.pbws.domain.entity.soundentity.quran

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class QuranFilesItem(
	val format: String? = null,
	val audioUrl: String? = null,
	val id: Int? = null,
	val chapterId: Int? = null,
):Parcelable