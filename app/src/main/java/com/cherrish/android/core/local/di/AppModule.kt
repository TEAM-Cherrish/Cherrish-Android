package com.cherrish.android.core.local.di

import com.cherrish.android.presentation.calendar.CalendarRefreshEventBus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCalendarRefreshEventBus(): CalendarRefreshEventBus {
        return CalendarRefreshEventBus()
    }
}
