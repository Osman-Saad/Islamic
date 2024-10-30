package com.pbws.data.di

import com.pbws.data.datasource.QuranDataSource
import com.pbws.data.datasource.QuranDetailsDataSource
import com.pbws.data.datasource.QuranTafasirDataSource
import com.pbws.data.datasourceimpl.QuranDataSourceImpl
import com.pbws.data.datasourceimpl.QuranDetailsDataSourceImpl
import com.pbws.data.datasourceimpl.QuranTafasirDataSourceImpl
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
}