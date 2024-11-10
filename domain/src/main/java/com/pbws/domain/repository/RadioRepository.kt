package com.pbws.domain.repository

import com.pbws.domain.entity.soundentity.quran.QuranSound
import com.pbws.domain.entity.soundentity.radio.RadioSound
import kotlinx.coroutines.flow.Flow

interface RadioRepository {
    suspend fun getRadioSound():RadioSound
    suspend fun getQuranSound(): QuranSound
}