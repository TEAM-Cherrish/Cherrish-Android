package com.cherrish.android.data.di

import com.cherrish.android.data.repository.CalendarRepository
import com.cherrish.android.data.repository.ChallengeMissionProgressRepository
import com.cherrish.android.data.repository.HomeRepository
import com.cherrish.android.data.repository.MyPageRepository
import com.cherrish.android.data.repository.OnboardingProfileRepository
import com.cherrish.android.data.repository.ProcedureRepository
import com.cherrish.android.data.repository.UserProcedureRepository
import com.cherrish.android.data.repository.WorryRepository
import com.cherrish.android.data.repositoryimpl.CalendarRepositoryImpl
import com.cherrish.android.data.repositoryimpl.ChallengeMissionProgressRepositoryImpl
import com.cherrish.android.data.repositoryimpl.HomeRepositoryImpl
import com.cherrish.android.data.repositoryimpl.MyPageRepositoryImpl
import com.cherrish.android.data.repositoryimpl.OnboardingProfileRepositoryImpl
import com.cherrish.android.data.repositoryimpl.ProcedureRepositoryImpl
import com.cherrish.android.data.repositoryimpl.UserProcedureRepositoryImpl
import com.cherrish.android.data.repositoryimpl.WorryRepositoryImpl
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
    abstract fun bindMyPageRepository(
        myPageRepositoryImpl: MyPageRepositoryImpl
    ): MyPageRepository

    @Binds
    @Singleton
    abstract fun bindProcedureRepository(
        procedureRepositoryImpl: ProcedureRepositoryImpl
    ): ProcedureRepository

    @Binds
    @Singleton
    abstract fun bindWorryRepository(
        worryRepositoryImpl: WorryRepositoryImpl
    ): WorryRepository

    @Binds
    @Singleton
    abstract fun bindUserProcedureRepository(
        userProcedureRepositoryImpl: UserProcedureRepositoryImpl
    ): UserProcedureRepository

    @Binds
    @Singleton
    abstract fun bindChallengeMissionProgressRepository(
        challengeMissionProgressRepositoryImpl: ChallengeMissionProgressRepositoryImpl
    ): ChallengeMissionProgressRepository
}
