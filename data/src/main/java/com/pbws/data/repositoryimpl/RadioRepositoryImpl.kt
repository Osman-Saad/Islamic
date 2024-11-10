package com.pbws.data.repositoryimpl

import com.pbws.data.constant.fromJson
import com.pbws.data.datasource.RadioDataSource
import com.pbws.domain.entity.soundentity.quran.QuranSound
import com.pbws.domain.entity.soundentity.radio.RadioSound
import com.pbws.domain.repository.RadioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RadioRepositoryImpl @Inject constructor(
    private val radioDataSource: RadioDataSource
) : RadioRepository {
    override suspend fun getRadioSound(): RadioSound {
        try {
            return radioDataSource.getRadioSound().fromJson(RadioSound::class.java)

        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getQuranSound(): QuranSound {
        try {
            return radioDataSource.getQuranSound().fromJson(QuranSound::class.java)

        } catch (ex: Exception) {
            throw ex
        }
    }
}