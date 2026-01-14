package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.CalendarDownTimeResponseDto

data class CalendarDownTimeResponseModel(
    val userProcedureId: Long,
    val scheduleAt: String,
    val downtimeDays: Int,
    val sensitiveDays: List<String>,
    val cautionDays: List<String>,
    val recoveryDays: List<String>
)

fun CalendarDownTimeResponseDto.toModel() = CalendarDownTimeResponseModel(
    userProcedureId = this.userProcedureId,
    scheduleAt = this.scheduleAt,
    downtimeDays = this.downtimeDays,
    sensitiveDays = this.sensitiveDays,
    cautionDays = this.cautionDays,
    recoveryDays = this.recoveryDays
)
