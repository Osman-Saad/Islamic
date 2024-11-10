package com.pbws.data.remote

import com.pbws.data.constant.DataConstant
import com.pbws.data.model.ahadeth.AhadethDto
import com.pbws.data.model.azkar.masaa.AzkarMasaa
import com.pbws.data.model.azkar.masaa.AzkarMasaaDto
import com.pbws.data.model.azkar.sabah.AzkarSabah
import com.pbws.data.model.azkar.sabah.AzkarSabahDto
import com.pbws.data.model.praytime.PrayTimeDto
import com.pbws.data.model.qurandetails.QuranDetailsDto
import com.pbws.data.model.quranmodel.QuranDto
import com.pbws.data.model.qurantafasir.QuranTafasirDto
import com.pbws.data.model.sound.quran.QuranSoundDto
import com.pbws.data.model.sound.radio.RadioSoundDto
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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

    @GET(DataConstant.AHADETH_END_POINT)
    suspend fun getAhadeth(
        @Query("page") page:Int,
        @Query("limit") pageSize: Int
    ):AhadethDto

    @GET(DataConstant.RADIO_SOUND_END_POINT)
    suspend fun getRadioSound():RadioSoundDto

    @GET(DataConstant.QURAN_SOUND_END_POINT)
    suspend fun getQuranSound(
        @Path("id") quranReader: Int = 1
    ):QuranSoundDto

    @GET(DataConstant.AZKAR_SABAH_END_POINT)
    suspend fun getAzkarSabah():AzkarSabahDto

    @GET(DataConstant.AZKAR_MASAA_END_POINT)
    suspend fun getAzkarMasaa():AzkarMasaaDto

    @GET(DataConstant.PRAY_TIME_END_POINT)
    suspend fun getPrayTime(
        @Path("date") date: String,
        @Query("city") city: String,
        @Query("country") country: String = "egypt",
        @Query("method") method: Int = 8
    ): PrayTimeDto

}

