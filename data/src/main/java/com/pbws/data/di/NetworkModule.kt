package com.pbws.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pbws.data.constant.DataConstant
import com.pbws.data.remote.ApiManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val contentType = DataConstant.CONTENT_TYPE.toMediaType()
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Singleton
    @Provides
    @QuranApiManager
    fun provideQuranRetrofitClient(): ApiManager {
        return Retrofit.Builder()
            .baseUrl(DataConstant.SUWAR_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiManager::class.java)
    }


    @Provides
    @Singleton
    @QuranDetailsApiManager
    fun provideQuranDetailsRetrofitClient(): ApiManager {
        return Retrofit.Builder()
            .baseUrl(DataConstant.QURAN_DETAILS_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
            .create(ApiManager::class.java)
    }

    @Provides
    @Singleton
    @QuranTafsirApiManager
    fun provideQuranTafasirRetrofitClient(): ApiManager {
        return Retrofit.Builder()
            .baseUrl(DataConstant.QURAN_TAFASIR_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiManager::class.java)
    }

    @Provides
    @Singleton
    @AhadethApiManager
    fun provideAhadethRetrofitClient(): ApiManager {
        return Retrofit.Builder()
            .baseUrl(DataConstant.AHADETH_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiManager::class.java)
    }

    @Provides
    @Singleton
    @RadioSoundApiManager
    fun provideRadioSoundRetrofitClient(): ApiManager {
        return Retrofit.Builder()
            .baseUrl(DataConstant.RADIO_SOUND_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiManager::class.java)
    }

    @Provides
    @Singleton
    @QuranSoundApiManager
    fun provideQuranSoundRetrofitClient(): ApiManager {
        return Retrofit.Builder()
            .baseUrl(DataConstant.QURAN_SOUND_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiManager::class.java)
    }

    @Provides
    @Singleton
    @AzkarApiManager
    fun provideAzkarRetrofitClient(): ApiManager {
        return Retrofit.Builder()
            .baseUrl(DataConstant.AZKAR_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiManager::class.java)
    }

    @Provides
    @Singleton
    @PrayTimeApiManager
    fun providePrayTimeRetrofitClient(): ApiManager {
        return Retrofit.Builder()
            .baseUrl(DataConstant.PRAY_TIME_BASEURL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiManager::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QuranApiManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QuranDetailsApiManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QuranTafsirApiManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AhadethApiManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RadioSoundApiManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QuranSoundApiManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AzkarApiManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrayTimeApiManager