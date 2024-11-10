package com.pbws.data.datasourceimpl

import com.pbws.data.datasource.RadioDataSource
import com.pbws.data.di.QuranSoundApiManager
import com.pbws.data.di.RadioSoundApiManager
import com.pbws.data.model.sound.quran.QuranSoundDto
import com.pbws.data.model.sound.radio.RadioSoundDto
import com.pbws.data.remote.ApiManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RadioDataSourceImpl @Inject constructor(
    @RadioSoundApiManager private val radioSoundApiManager: ApiManager,
    @QuranSoundApiManager private val quranSoundApiManager: ApiManager
) :RadioDataSource {
    override suspend fun getRadioSound(): RadioSoundDto {
        try {
            return radioSoundApiManager.getRadioSound()
        }catch (ex:Exception){
            throw ex
        }
    }

    override suspend fun getQuranSound(): QuranSoundDto {
        try {
            return quranSoundApiManager.getQuranSound()
        }catch (ex:Exception){
            throw ex
        }
    }
}