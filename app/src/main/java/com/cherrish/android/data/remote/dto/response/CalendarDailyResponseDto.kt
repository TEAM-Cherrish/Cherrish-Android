package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarDailyResponseDto(
    @SerialName("eventCount")
    val eventCount: Int,
    @SerialName("events")
    val events: List<EventDto>
)

@Serializable
data class EventDto(
    @SerialName("type")
    val type: String,
    @SerialName("userProcedureId")
    val userProcedureId: Long,
    @SerialName("procedureId")
    val procedureId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("scheduledAt")
    val scheduledAt: String,
    @SerialName("downtimeDays")
    val downtimeDays: Int
)
