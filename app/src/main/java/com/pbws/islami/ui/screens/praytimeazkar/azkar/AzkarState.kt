package com.pbws.islami.ui.screens.praytimeazkar.azkar

sealed class AzkarState {
    data object Loading : AzkarState()
    data object Ideal : AzkarState()
    data class Error(val message: String) : AzkarState()
    data class Success(val azkar: Any) : AzkarState()
}