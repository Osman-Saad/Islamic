package com.pbws.domain.repository

import com.pbws.domain.entity.qurandetailsentity.Data
import com.pbws.domain.entity.qurandetailsentity.QuranDetails

interface QuranDetailsRepository {
    suspend fun getQuranDetails(suraNumber:Int): QuranDetails

}