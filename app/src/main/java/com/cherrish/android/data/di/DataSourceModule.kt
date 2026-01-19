package com.cherrish.android.data.di

import com.cherrish.android.data.remote.datasource.CalendarDataSource
import com.cherrish.android.data.remote.datasource.ProcedureDataSource
import com.cherrish.android.data.remote.datasource.UserProcedureDataSource
import com.cherrish.android.data.remote.datasource.WorryDataSource
import com.cherrish.android.data.remote.datasourceimpl.CalendarDataSourceImpl
import com.cherrish.android.data.remote.datasourceimpl.ProcedureDataSourceImpl
import com.cherrish.android.data.remote.datasourceimpl.UserProcedureDataSourceImpl
import com.cherrish.android.data.remote.datasourceimpl.WorryDataSourceImpl
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
    abstract fun bindProcedureDataSource(
        procedureDataSourceImpl: ProcedureDataSourceImpl
    ): ProcedureDataSource

    @Binds
    @Singleton
    abstract fun bindWorryDataSource(
        worryDataSourceImpl: WorryDataSourceImpl
    ): WorryDataSource

    @Binds
    @Singleton
    abstract fun bindUserProcedureDataSource(
        userProcedureDataSourceImpl: UserProcedureDataSourceImpl
    ): UserProcedureDataSource
}
