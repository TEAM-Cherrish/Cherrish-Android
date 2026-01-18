package com.cherrish.android.data.di

import com.cherrish.android.data.remote.service.CalendarService
import com.cherrish.android.data.remote.service.OnboardingProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideCalendarService(
        retrofit: Retrofit
    ): CalendarService = retrofit.create(CalendarService::class.java)

    @Provides
    @Singleton
    fun provideOnboardingProfileService(
        retrofit: Retrofit
    ): OnboardingProfileService = retrofit.create(OnboardingProfileService::class.java)
}
