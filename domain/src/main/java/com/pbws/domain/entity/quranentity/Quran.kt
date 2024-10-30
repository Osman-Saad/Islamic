package com.pbws.domain.entity.quranentity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quran(

    val suwar: List<SuwarItem?>? = null
):Parcelable