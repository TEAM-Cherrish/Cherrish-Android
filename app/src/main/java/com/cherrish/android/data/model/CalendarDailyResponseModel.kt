package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.CalendarDailyResponseDto
import com.cherrish.android.data.remote.dto.response.EventDto

data class CalendarDailyResponseModel(
    val eventCount: Int,
    val events: List<EventModel>
)

data class EventModel(
    val type: String,
    val userProcedureId: Long,
    val procedureId: Long,
    val name: String,
    val scheduledAt: String,
    val downtimeDays: Int,
)

fun CalendarDailyResponseDto.toModel() = CalendarDailyResponseModel(
    eventCount = this.eventCount,
    events = this.events.map { it.toModel() }
)

fun EventDto.toModel() = EventModel(
    type = this.type,
    userProcedureId = this.userProcedureId,
    procedureId = this.procedureId,
    name = this.name,
    scheduledAt = this.scheduledAt,
    downtimeDays = this.downtimeDays,
)