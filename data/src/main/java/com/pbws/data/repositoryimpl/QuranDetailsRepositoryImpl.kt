package com.pbws.data.repositoryimpl

import com.pbws.data.constant.fromJson
import com.pbws.data.datasource.QuranDetailsDataSource
import com.pbws.data.model.qurandetails.QuranDetailsDto
import com.pbws.domain.entity.qurandetailsentity.Data
import com.pbws.domain.entity.qurandetailsentity.QuranDetails
import com.pbws.domain.repository.QuranDetailsRepository
import javax.inject.Inject

class QuranDetailsRepositoryImpl @Inject constructor(
    private val quranDetailsDataSource: QuranDetailsDataSource
):QuranDetailsRepository {
    override suspend fun getQuranDetails(suraNumber: Int): QuranDetails {
        try {
            return quranDetailsDataSource.getQuranDetails(suraNumber).fromJson(QuranDetails::class.java)
        }catch (ex:Exception){
            throw ex
        }
    }

}