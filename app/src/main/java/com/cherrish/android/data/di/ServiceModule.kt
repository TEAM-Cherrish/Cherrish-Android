package com.cherrish.android.data.di

import com.cherrish.android.data.remote.service.CalendarService
import com.cherrish.android.data.remote.service.DummyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideCalendarService(
        retrofit: Retrofit
    ): CalendarService = retrofit.create(CalendarService::class.java)
}
