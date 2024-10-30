package com.pbws.domain.repository

import com.pbws.domain.entity.qurantafasirentity.QuranTafasir

interface QuranTafasirRepository {
    suspend fun getQuranTafasir(suraNumber: Int): QuranTafasir

}