package com.cherrish.android.data.di

import com.cherrish.android.data.remote.service.CalendarService
import com.cherrish.android.data.remote.service.ChallengeMissionProgressService
import com.cherrish.android.data.remote.service.ChallengeService
import com.cherrish.android.data.remote.service.HomeService
import com.cherrish.android.data.remote.service.MyPageService
import com.cherrish.android.data.remote.service.OnboardingProfileService
import com.cherrish.android.data.remote.service.ProcedureService
import com.cherrish.android.data.remote.service.UserProcedureService
import com.cherrish.android.data.remote.service.WorryService
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

    @Provides
    @Singleton
    fun provideHomeService(
        retrofit: Retrofit
    ): HomeService = retrofit.create(HomeService::class.java)

    @Provides
    @Singleton
    fun provideOnboardingProfileService(
        retrofit: Retrofit
    ): OnboardingProfileService = retrofit.create(OnboardingProfileService::class.java)

    @Provides
    @Singleton
    fun provideProcedureService(retrofit: Retrofit): ProcedureService =
        retrofit.create(ProcedureService::class.java)

    @Provides
    @Singleton
    fun provideWorryService(retrofit: Retrofit): WorryService =
        retrofit.create(WorryService::class.java)

    @Provides
    @Singleton
    fun provideUserProcedureService(retrofit: Retrofit): UserProcedureService =
        retrofit.create(UserProcedureService::class.java)

    @Provides
    @Singleton
    fun provideChallengeService(
        retrofit: Retrofit
    ): ChallengeService = retrofit.create(ChallengeService::class.java)

    @Provides
    @Singleton
    fun provideMyPageService(
        retrofit: Retrofit
    ): MyPageService = retrofit.create(MyPageService::class.java)

    @Provides
    @Singleton
    fun provideChallengeMissionProgressService(
        retrofit: Retrofit
    ): ChallengeMissionProgressService = retrofit.create(
        ChallengeMissionProgressService::class.java
    )
}
