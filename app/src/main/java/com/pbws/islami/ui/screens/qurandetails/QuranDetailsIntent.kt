package com.pbws.islami.ui.screens.qurandetails

sealed class QuranDetailsIntent {
    data class GetQuranDetails(val suraNumber: Int) : QuranDetailsIntent()
}