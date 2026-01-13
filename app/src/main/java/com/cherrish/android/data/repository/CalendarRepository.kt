package com.cherrish.android.data.repository

import com.cherrish.android.data.model.CalendarMonthlyResponseModel

interface CalendarRepository {
    suspend fun getCalendarMonthly(
        year: Int,
        month: Int
    ): Result<CalendarMonthlyResponseModel>
}