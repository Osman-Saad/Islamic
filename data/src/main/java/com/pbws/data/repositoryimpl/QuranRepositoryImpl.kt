package com.pbws.data.repositoryimpl

import com.pbws.data.constant.fromJson
import com.pbws.data.datasource.QuranDataSource
import com.pbws.domain.entity.quranentity.Quran
import com.pbws.domain.repository.quranrepo.QuranRepository
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor(private val quranDataSource: QuranDataSource):QuranRepository {
    override suspend fun getSuwar(): Quran {
        try {
           return quranDataSource.getSuwar().fromJson(Quran::class.java)
        }catch (ex:Exception){
            throw ex
        }
    }
}