package com.pbws.islami.ui.screens.radio

sealed class RadioIntent {
    data object GetSounds : RadioIntent()
}