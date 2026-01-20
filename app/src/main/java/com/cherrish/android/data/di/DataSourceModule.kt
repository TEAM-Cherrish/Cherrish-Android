package com.cherrish.android.data.di

import com.cherrish.android.data.remote.datasource.CalendarDataSource
import com.cherrish.android.data.remote.datasource.ChallengeMissionProgressDataSource
import com.cherrish.android.data.remote.datasource.HomeDataSource
import com.cherrish.android.data.remote.datasource.MyPageDataSource
import com.cherrish.android.data.remote.datasource.OnboardingProfileDataSource
import com.cherrish.android.data.remote.datasourceimpl.CalendarDataSourceImpl
import com.cherrish.android.data.remote.datasourceimpl.ChallengeMissionProgressDataSourceImpl
import com.cherrish.android.data.remote.datasourceimpl.HomeDataSourceImpl
import com.cherrish.android.data.remote.datasourceimpl.MyPageDataSourceImpl
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
    abstract fun bindMyPageDataSource(
        myPageDataSourceImpl: MyPageDataSourceImpl
    ): MyPageDataSource

    @Binds
    @Singleton
    abstract fun bindChallengeMissionProgressDataSource(
        challengeMissionProgressDataSourceImpl: ChallengeMissionProgressDataSourceImpl
    ): ChallengeMissionProgressDataSource
}
