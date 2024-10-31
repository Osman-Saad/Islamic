package com.pbws.data.di

import com.pbws.data.repositoryimpl.AhadethRepositoryImpl
import com.pbws.data.repositoryimpl.QuranDetailsRepositoryImpl
import com.pbws.data.repositoryimpl.QuranRepositoryImpl
import com.pbws.data.repositoryimpl.QuranTafasirRepositoryImpl
import com.pbws.domain.repository.AhadethRepository
import com.pbws.domain.repository.QuranDetailsRepository
import com.pbws.domain.repository.QuranTafasirRepository
import com.pbws.domain.repository.quranrepo.QuranRepository
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
}