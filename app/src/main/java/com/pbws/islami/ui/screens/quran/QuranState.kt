package com.pbws.islami.ui.screens.quran

import com.pbws.domain.entity.quranentity.SuwarItem

sealed class QuranState {
    data object Ideal:QuranState()
    data object Loading:QuranState()
    data class Success(val quranItems:List<SuwarItem>):QuranState()
    data class Error(val message:String):QuranState()
}