package com.pbws.islami.ui.screens.radio

import com.pbws.domain.entity.soundentity.quran.QuranFilesItem
import com.pbws.domain.entity.soundentity.radio.RadioSoundItem

sealed class RadioState {
    data object Ideal : RadioState()
    data object Loading : RadioState()
    data class RadioAndQuranSoundsRetrievedSuccess(
        val radioSounds: List<RadioSoundItem>,
        val quranSounds: List<QuranFilesItem>
    ):RadioState()
    data class Error(val message:String):RadioState()
}