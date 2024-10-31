package com.pbws.islami.di

import com.pbws.domain.repository.AhadethRepository
import com.pbws.domain.repository.QuranDetailsRepository
import com.pbws.domain.repository.QuranTafasirRepository
import com.pbws.domain.repository.quranrepo.QuranRepository
import com.pbws.domain.usecases.AhadethUseCase
import com.pbws.domain.usecases.QuranDetailsUseCase
import com.pbws.domain.usecases.QuranTafasirUseCase
import com.pbws.domain.usecases.QuranUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Singleton
    @Provides
    fun provideQuranUseCase(quranRepository: QuranRepository):QuranUseCase{
        return QuranUseCase(quranRepository)
    }
    @Singleton
    @Provides
    fun provideQuranDetailsUseCase(quranDetailsRepository: QuranDetailsRepository):QuranDetailsUseCase{
        return QuranDetailsUseCase(quranDetailsRepository)
    }
    @Singleton
    @Provides
    fun provideQuranTafasirUseCase(quranTafasirRepository: QuranTafasirRepository):QuranTafasirUseCase{
        return QuranTafasirUseCase(quranTafasirRepository)
    }

    @Singleton
    @Provides
    fun provideAhadethUseCase(ahadethRepository: AhadethRepository):AhadethUseCase{
        return AhadethUseCase(ahadethRepository)
    }
}