package com.cherrish.android.data.di

import com.cherrish.android.data.remote.datasource.CalendarDataSource
import com.cherrish.android.data.remote.datasource.ChallengeDataSource
import com.cherrish.android.data.remote.datasource.HomeDataSource
import com.cherrish.android.data.remote.datasource.OnboardingProfileDataSource
import com.cherrish.android.data.remote.datasourceimpl.CalendarDataSourceImpl
import com.cherrish.android.data.remote.datasourceimpl.ChallengeDataSourceImpl
import com.cherrish.android.data.remote.datasourceimpl.HomeDataSourceImpl
import com.cherrish.android.data.remote.datasourceimpl.OnboardingProfileDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun bindHomeDataSource(
        homeDataSourceImpl: HomeDataSourceImpl
    ): HomeDataSource

    @Binds
    @Singleton
    abstract fun bindOnboardingProfileDataSource(
        onboardingProfileDataSourceImpl: OnboardingProfileDataSourceImpl
    ): OnboardingProfileDataSource

    @Binds
    @Singleton
    abstract fun challengeDataSource(
        challengeDataSourceImpl: ChallengeDataSourceImpl
    ): ChallengeDataSource
}
