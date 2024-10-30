package com.pbws.data.datasource

import com.pbws.data.model.qurantafasir.QuranTafasirDto

interface QuranTafasirDataSource {
    suspend fun getQuranTafasir(suraNumber: Int): QuranTafasirDto
}