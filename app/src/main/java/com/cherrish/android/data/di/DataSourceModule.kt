package com.cherrish.android.data.di

import com.cherrish.android.data.datasource.DummyDataSource
import com.cherrish.android.data.datasourceimpl.DummyDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindDummyDataSource(
        dummyDataSourceImpl: DummyDataSourceImpl
    ): DummyDataSource
}
