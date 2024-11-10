package com.pbws.data.di

import com.pbws.data.repositoryimpl.AhadethRepositoryImpl
import com.pbws.data.repositoryimpl.AzkarRepositoryImpl
import com.pbws.data.repositoryimpl.PrayTimeRepositoryImpl
import com.pbws.data.repositoryimpl.QuranDetailsRepositoryImpl
import com.pbws.data.repositoryimpl.QuranRepositoryImpl
import com.pbws.data.repositoryimpl.QuranTafasirRepositoryImpl
import com.pbws.data.repositoryimpl.RadioRepositoryImpl
import com.pbws.domain.repository.AhadethRepository
import com.pbws.domain.repository.AzkarRepository
import com.pbws.domain.repository.PrayTimeRepository
import com.pbws.domain.repository.QuranDetailsRepository
import com.pbws.domain.repository.QuranTafasirRepository
import com.pbws.domain.repository.RadioRepository
import com.pbws.domain.repository.QuranRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideQuranRepository(quranRepositoryImpl: QuranRepositoryImpl): QuranRepository

    @Binds
    abstract fun provideQuranDetailsRepository(quranDetailsRepositoryImpl: QuranDetailsRepositoryImpl):QuranDetailsRepository
    @Binds
    abstract fun provideQuranTafasirRepository(quranTafasirRepositoryImpl: QuranTafasirRepositoryImpl):QuranTafasirRepository

    @Binds
    abstract fun provideAhadethRepository(ahadethRepositoryImpl: AhadethRepositoryImpl): AhadethRepository

    @Binds
    abstract fun provideRadioRepository(radioRepositoryImpl: RadioRepositoryImpl):RadioRepository

    @Binds
    abstract fun provideAzkarRepository(azkarRepositoryImpl: AzkarRepositoryImpl):AzkarRepository

    @Binds
    abstract fun providePrayTimeRepository(prayTimeRepositoryImpl:PrayTimeRepositoryImpl):PrayTimeRepository
}