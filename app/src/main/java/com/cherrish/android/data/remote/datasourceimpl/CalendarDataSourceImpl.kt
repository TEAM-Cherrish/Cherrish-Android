package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.CalendarDataSource
import com.cherrish.android.data.remote.dto.response.CalendarDailyResponseDto
import com.cherrish.android.data.remote.dto.response.CalendarDownTimeResponseDto
import com.cherrish.android.data.remote.dto.response.CalendarMonthlyResponseDto
import com.cherrish.android.data.remote.service.CalendarService
import javax.inject.Inject

class CalendarDataSourceImpl @Inject constructor(
    private val calendarService: CalendarService
) : CalendarDataSource {
    override suspend fun getCalendarMonthly(
        year: Int,
        month: Int
    ): BaseResponse<CalendarMonthlyResponseDto> =
        calendarService.getCalendarMonthly(year = year, month = month)

    override suspend fun getCalendarDaily(
        date: String
    ): BaseResponse<CalendarDailyResponseDto> =
        calendarService.getCalendarDaily(date = date)

    override suspend fun getCalendarEventDowntime(
        id: Long
    ): BaseResponse<CalendarDownTimeResponseDto> =
        calendarService.getCalendarEventDowntime(id = id)
}
