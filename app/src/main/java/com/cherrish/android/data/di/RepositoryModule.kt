package com.cherrish.android.data.di

import com.cherrish.android.data.repository.CalendarRepository
import com.cherrish.android.data.repository.DummyRepository
import com.cherrish.android.data.repositoryimpl.CalendarRepositoryImpl
import com.cherrish.android.data.repositoryimpl.DummyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCalendarRepository(
        calendarRepositoryImpl: CalendarRepositoryImpl
    ): CalendarRepository
}
