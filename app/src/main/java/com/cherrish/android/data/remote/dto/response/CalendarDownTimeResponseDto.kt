package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarDownTimeResponseDto(
    @SerialName("userProcedureId")
    val userProcedureId: Long,
    @SerialName("scheduleAt")
    val scheduleAt: String,
    @SerialName("downtimeDays")
    val downtimeDays: Int,
    @SerialName("sensitiveDays")
    val sensitiveDays: List<String>,
    @SerialName("cautionDays")
    val cautionDays: List<String>,
    @SerialName("recoveryDays")
    val recoveryDays: List<String>
)