package com.pbws.data.datasourceimpl

import android.util.Log
import com.pbws.data.datasource.QuranDetailsDataSource
import com.pbws.data.di.QuranDetailsApiManager
import com.pbws.data.model.qurandetails.QuranDetailsDto
import com.pbws.data.remote.ApiManager
import javax.inject.Inject

class QuranDetailsDataSourceImpl @Inject constructor(
    @QuranDetailsApiManager private val apiManager: ApiManager
) :QuranDetailsDataSource {
    override suspend fun getQuranDetails(suraNumber: Int): QuranDetailsDto {
        try {
            val data = apiManager.getQuranDetails(suraNumber)
            Log.d("TA",data.data?.englishName.toString())
            return apiManager.getQuranDetails(suraNumber)
        }catch (ex:Exception){
            throw ex
        }
    }

}