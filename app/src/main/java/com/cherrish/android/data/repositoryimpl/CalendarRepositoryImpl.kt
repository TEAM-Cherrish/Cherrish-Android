package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.data.model.CalendarDailyResponseModel
import com.cherrish.android.data.model.CalendarMonthlyResponseModel
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.CalendarDataSource
import com.cherrish.android.data.repository.CalendarRepository
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarDataSource: CalendarDataSource
) : CalendarRepository {
    override suspend fun getCalendarMonthly(
        year: Int,
        month: Int
    ): Result<CalendarMonthlyResponseModel> =
        runCatching {
            calendarDataSource.getCalendarMonthly(year = year, month = month).data!!.toModel()
        }

    override suspend fun getCalendarDaily(date: String): Result<CalendarDailyResponseModel> =
        runCatching {
            calendarDataSource.getCalendarDaily(date = date).data!!.toModel()
        }
}
