package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.CalendarMonthlyResponseDto

interface CalendarDataSource {
    suspend fun getCalendarMonthly(year: Int, month: Int): BaseResponse<CalendarMonthlyResponseDto>
}
