package com.pbws.data.remote

import com.pbws.data.constant.DataConstant
import com.pbws.data.model.qurandetails.DataDto
import com.pbws.data.model.qurandetails.QuranDetailsDto
import com.pbws.data.model.quranmodel.QuranDto
import com.pbws.data.model.qurantafasir.QuranTafasirDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiManager {
    @GET(DataConstant.SUWAR_END_POINT)
    suspend fun getSuwar(
        @Query("language") language: String = "ar"
    ): QuranDto

    @GET(DataConstant.QURAN_DETAILS_END_POINT)
    suspend fun getQuranDetails(
        @Path("surah") suraNumber: Int
    ): QuranDetailsDto

    @GET(DataConstant.QURAN_TAFASIR_END_POINT)
    suspend fun getQuranTafasir(
        @Path("surah") suraNumber: Int
    ):QuranTafasirDto
}
