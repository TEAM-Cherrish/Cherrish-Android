package com.cherrish.android.data.di

import com.cherrish.android.data.repository.CalendarRepository
import com.cherrish.android.data.repository.OnboardingProfileRepository
import com.cherrish.android.data.repositoryimpl.CalendarRepositoryImpl
import com.cherrish.android.data.repositoryimpl.OnboardingProfileRepositoryImpl
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

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindOnboardingProfileRepository(
        onboardingProfileRepositoryImpl: OnboardingProfileRepositoryImpl
    ): OnboardingProfileRepository

    @Binds
    @Singleton
    abstract fun bindChallengeRepository(
        challengeRepositoryImpl: ChallengeRepositoryImpl
    ): ChallengeRepository

    @Binds
    @Singleton
    abstract fun bindMyPageRepository(
        myPageRepositoryImpl: MyPageRepositoryImpl
    ): MyPageRepository

    @Binds
    @Singleton
    abstract fun bindChallengeMissionProgressRepository(
        challengeMissionProgressRepositoryImpl: ChallengeMissionProgressRepositoryImpl
    ): ChallengeMissionProgressRepository
}
