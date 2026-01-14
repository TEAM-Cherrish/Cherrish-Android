package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.CalendarMonthlyResponseDto

data class CalendarMonthlyResponseModel(
    val dailyProcedureCounts: Map<Int, Long>
)

fun CalendarMonthlyResponseDto.toModel() = CalendarMonthlyResponseModel(
    dailyProcedureCounts = this.dailyProcedureCounts
)
