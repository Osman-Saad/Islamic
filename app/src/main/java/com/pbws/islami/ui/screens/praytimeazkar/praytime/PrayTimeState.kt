package com.pbws.islami.ui.screens.praytimeazkar.praytime

import com.pbws.domain.entity.praytimeentity.PrayTime

sealed class PrayTimeState {
    data object Ideal : PrayTimeState()
    data object Loading : PrayTimeState()
    data class Success(val prayTime: PrayTime) : PrayTimeState()
    data class Error(val message: String) : PrayTimeState()
}