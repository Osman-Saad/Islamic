package com.pbws.islami.ui.screens.qurandetails

import com.pbws.domain.entity.qurandetailsentity.QuranDetails
import com.pbws.domain.entity.qurantafasirentity.QuranTafasir
import com.pbws.domain.entity.qurantafasirentity.QuranTafasirItem

sealed class QuranDetailsState {
    data object Ideal: QuranDetailsState()
    data object Loading : QuranDetailsState()
    data class Success(val quranDetails: QuranDetails,val quranTafasir:QuranTafasir) : QuranDetailsState()
    data class Error(val message:String) : QuranDetailsState()
}