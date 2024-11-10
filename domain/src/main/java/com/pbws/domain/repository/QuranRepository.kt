package com.pbws.domain.repository

import com.pbws.domain.entity.quranentity.Quran
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    suspend fun getSuwar():Quran
}