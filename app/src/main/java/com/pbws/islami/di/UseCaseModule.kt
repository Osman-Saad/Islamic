package com.pbws.islami.di

import com.pbws.domain.repository.AhadethRepository
import com.pbws.domain.repository.AzkarRepository
import com.pbws.domain.repository.PrayTimeRepository
import com.pbws.domain.repository.QuranDetailsRepository
import com.pbws.domain.repository.QuranTafasirRepository
import com.pbws.domain.repository.QuranRepository
import com.pbws.domain.repository.RadioRepository
import com.pbws.domain.usecases.AhadethUseCase
import com.pbws.domain.usecases.AzkarMasaaUseCase
import com.pbws.domain.usecases.AzkarSabahUseCase
import com.pbws.domain.usecases.PrayTimeUseCase
import com.pbws.domain.usecases.QuranDetailsUseCase
import com.pbws.domain.usecases.QuranSoundUseCase
import com.pbws.domain.usecases.QuranTafasirUseCase
import com.pbws.domain.usecases.QuranUseCase
import com.pbws.domain.usecases.RadioSoundUseCase
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

    @Singleton
    @Provides
    fun provideRadioSoundUseCase(radioRepository:RadioRepository):RadioSoundUseCase{
        return RadioSoundUseCase(radioRepository)
    }

    @Singleton
    @Provides
    fun provideQuranSoundUseCase(radioRepository:RadioRepository): QuranSoundUseCase {
        return QuranSoundUseCase(radioRepository)
    }

    @Singleton
    @Provides
    fun provideAzkarSabahUseCase(azkarRepository: AzkarRepository): AzkarSabahUseCase{
        return AzkarSabahUseCase(azkarRepository)
    }

    @Singleton
    @Provides
    fun provideAzkarMasaaUseCase(azkarRepository: AzkarRepository):AzkarMasaaUseCase{
        return AzkarMasaaUseCase(azkarRepository)
    }

    @Singleton
    @Provides
    fun providePrayTimeUseCase(prayTimeRepository:PrayTimeRepository):PrayTimeUseCase{
        return PrayTimeUseCase(prayTimeRepository)
    }
}