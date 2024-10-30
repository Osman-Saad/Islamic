package com.pbws.data.datasource

import com.pbws.data.model.qurandetails.DataDto
import com.pbws.data.model.qurandetails.QuranDetailsDto

interface QuranDetailsDataSource {
    suspend fun getQuranDetails(suraNumber:Int): QuranDetailsDto
}