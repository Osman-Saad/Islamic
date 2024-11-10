package com.pbws.islami.ui.screens.splash

import com.pbws.domain.entity.praytimeentity.PrayTime
import com.pbws.domain.entity.quranentity.SuwarItem

sealed class SplashState{
    data object Ideal: SplashState()
    data object Loading: SplashState()
    data class Success(val quranItems:List<SuwarItem>): SplashState()
    data class Error(val message:String): SplashState()
}