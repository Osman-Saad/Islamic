package com.pbws.data.datasource

import com.pbws.data.model.quranmodel.QuranDto

interface QuranDataSource {
    suspend fun getSuwar():QuranDto
}