package com.pbws.islami.ui.screens.splash

sealed class SplashIntent {
    data object GetSuwar: SplashIntent()
    data class GetPrayTime(val city: String): SplashIntent()
}