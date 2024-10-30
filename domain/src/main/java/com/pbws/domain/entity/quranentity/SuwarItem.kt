package com.pbws.domain.entity.quranentity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuwarItem(
//    val endPage: Int? = null,
//    val startPage: Int? = null,
    val makkia: Int? = null,
    val name: String? = null,
    val id: Int? = null,
    val type: Int? = null
):Parcelable