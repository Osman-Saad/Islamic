package com.pbws.data.datasourceimpl

import com.pbws.data.datasource.QuranDataSource
import com.pbws.data.di.QuranApiManager
import com.pbws.data.model.quranmodel.QuranDto
import com.pbws.data.remote.ApiManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuranDataSourceImpl @Inject constructor(
    @QuranApiManager private val apiManager: ApiManager):QuranDataSource {
    override suspend fun getSuwar(): QuranDto {
        try {
            return apiManager.getSuwar()
        }catch (ex:Exception){
            throw ex
        }
    }
}