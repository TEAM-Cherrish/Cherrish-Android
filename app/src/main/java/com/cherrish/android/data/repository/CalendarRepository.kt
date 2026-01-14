package com.cherrish.android.data.repository

import com.cherrish.android.data.model.CalendarDailyResponseModel
import com.cherrish.android.data.model.CalendarDownTimeResponseModel
import com.cherrish.android.data.model.CalendarMonthlyResponseModel

interface CalendarRepository {
    suspend fun getCalendarMonthly(
        year: Int,
        month: Int
    ): Result<CalendarMonthlyResponseModel>

    suspend fun getCalendarDaily(
        date: String
    ): Result<CalendarDailyResponseModel>

    suspend fun getCalendarEventDowntime(
        id: Long
    ): Result<CalendarDownTimeResponseModel>
}
