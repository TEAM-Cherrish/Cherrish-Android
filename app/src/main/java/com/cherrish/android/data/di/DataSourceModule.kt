package com.cherrish.android.data.di

import com.cherrish.android.data.remote.datasource.CalendarDataSource
import com.cherrish.android.data.remote.datasourceimpl.CalendarDataSourceImpl
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
    abstract fun bindCalendarDataSource(
        calendarDataSourceImpl: CalendarDataSourceImpl
    ): CalendarDataSource
}
