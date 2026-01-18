package com.cherrish.android.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarDailyRequestDto(
    @SerialName("date")
    val date: String
)
