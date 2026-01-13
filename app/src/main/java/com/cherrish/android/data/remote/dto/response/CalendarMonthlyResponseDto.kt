package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarMonthlyResponseDto(
    @SerialName("dailyProcedureCounts")
    val dailyProcedureCounts: Map<Int, Long>?
)
