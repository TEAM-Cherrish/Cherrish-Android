package com.cherrish.android.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarMonthlyRequestDto(
    @SerialName("year")
    val year: Int,
    @SerialName("month")
    val month: Int
)
