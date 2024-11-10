package com.pbws.islami.ui.screens.praytimeazkar.praytime

sealed class PrayTimeIntent {
    data class GetPrayTime(val city: String):PrayTimeIntent()
}