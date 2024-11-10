package com.pbws.islami.ui.screens.radio

import com.pbws.domain.entity.soundentity.quran.QuranFilesItem
import com.pbws.domain.entity.soundentity.radio.RadioSoundItem

sealed class Radio{
    data class QuranSound(val quranItems:List<QuranFilesItem>):Radio()
    data class RadioSound(val radioItems:List<RadioSoundItem>):Radio()
}