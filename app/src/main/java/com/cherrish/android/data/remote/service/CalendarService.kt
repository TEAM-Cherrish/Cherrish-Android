package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.CalendarDailyResponseDto
import com.cherrish.android.data.remote.dto.response.CalendarMonthlyResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CalendarService {
    @GET("api/calendar/monthly")
    suspend fun getCalendarMonthly(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): BaseResponse<CalendarMonthlyResponseDto>

    @GET("api/calendar/daily")
    suspend fun getCalendarDaily(
        @Query("date") date: String
    ): BaseResponse<CalendarDailyResponseDto>
}
