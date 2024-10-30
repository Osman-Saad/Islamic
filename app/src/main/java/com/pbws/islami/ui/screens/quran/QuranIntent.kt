package com.pbws.islami.ui.screens.quran

sealed class QuranIntent {
    data object GetQuran:QuranIntent()
}