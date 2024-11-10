package com.pbws.data.di

import com.pbws.data.datasource.AzkarDataSource
import com.pbws.data.datasource.PrayTimeDataSource
import com.pbws.data.datasource.QuranDataSource
import com.pbws.data.datasource.QuranDetailsDataSource
import com.pbws.data.datasource.QuranTafasirDataSource
import com.pbws.data.datasource.RadioDataSource
import com.pbws.data.datasourceimpl.AzkarDataSourceImpl
import com.pbws.data.datasourceimpl.PrayTimeDataSourceImpl
import com.pbws.data.datasourceimpl.QuranDataSourceImpl
import com.pbws.data.datasourceimpl.QuranDetailsDataSourceImpl
import com.pbws.data.datasourceimpl.QuranTafasirDataSourceImpl
import com.pbws.data.datasourceimpl.RadioDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideQuranDataSource(quranDataSourceImpl: QuranDataSourceImpl):QuranDataSource

    @Binds
    abstract fun provideQuranDetailsDataSource(quranDetailsDataSourceImpl: QuranDetailsDataSourceImpl): QuranDetailsDataSource

    @Binds
    abstract fun provideQuranTafasirDataSource(quranTafasirDataSourceImpl: QuranTafasirDataSourceImpl): QuranTafasirDataSource

    @Binds
    abstract fun provideRadioDataSource(radioDataSourceImpl:RadioDataSourceImpl):RadioDataSource
    @Binds
    abstract fun provideAzkarDataSource(azkarDataSourceImpl: AzkarDataSourceImpl):AzkarDataSource

    @Binds
    abstract fun providePrayTimeDataSource(prayTimeDataSourceImpl: PrayTimeDataSourceImpl):PrayTimeDataSource

}