package com.pbws.data.datasourceimpl

import com.pbws.data.datasource.QuranTafasirDataSource
import com.pbws.data.di.QuranTafsirApiManager
import com.pbws.data.model.qurantafasir.QuranTafasirDto
import com.pbws.data.remote.ApiManager
import javax.inject.Inject

class QuranTafasirDataSourceImpl @Inject constructor(
    @QuranTafsirApiManager private val apiManager: ApiManager
):QuranTafasirDataSource {
    override suspend fun getQuranTafasir(suraNumber: Int): QuranTafasirDto {
        try {
            return apiManager.getQuranTafasir(suraNumber)
        }catch (ex:Exception){
            throw ex
        }
    }
}