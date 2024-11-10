package com.pbws.data.datasource

import com.pbws.data.model.sound.quran.QuranSoundDto
import com.pbws.data.model.sound.radio.RadioSoundDto

interface RadioDataSource {
    suspend fun getRadioSound(): RadioSoundDto
    suspend fun getQuranSound(): QuranSoundDto
}