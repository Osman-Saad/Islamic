package com.pbws.domain.repository.quranrepo

import com.pbws.domain.entity.quranentity.Quran

interface QuranRepository {
    suspend fun getSuwar():Quran
}