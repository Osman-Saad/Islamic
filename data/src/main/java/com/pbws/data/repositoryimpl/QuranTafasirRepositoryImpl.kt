package com.pbws.data.repositoryimpl

import com.pbws.data.constant.fromJson
import com.pbws.data.datasource.QuranTafasirDataSource
import com.pbws.domain.entity.qurantafasirentity.QuranTafasir
import com.pbws.domain.repository.QuranTafasirRepository
import javax.inject.Inject

class QuranTafasirRepositoryImpl @Inject constructor(
    private val quranTafasirDataSource: QuranTafasirDataSource
) : QuranTafasirRepository {
    override suspend fun getQuranTafasir(suraNumber: Int): QuranTafasir {
        try {
            return quranTafasirDataSource.getQuranTafasir(suraNumber)
                .fromJson(QuranTafasir::class.java)
        } catch (ex: Exception) {
            throw ex
        }
    }
}