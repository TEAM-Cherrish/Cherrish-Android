package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.CalendarDownTimeResponseDto

data class CalendarDownTimeResponseModel(
    val userProcedureId: Long,
    val scheduledAt: String,
    val downtimeDays: Int,
    val recoveryTargetDate: String,
    val sensitiveDays: List<String>,
    val cautionDays: List<String>,
    val recoveryDays: List<String>
)

fun CalendarDownTimeResponseDto.toModel() = CalendarDownTimeResponseModel(
    userProcedureId = this.userProcedureId,
    scheduledAt = this.scheduledAt,
    downtimeDays = this.downtimeDays,
    recoveryTargetDate = this.recoveryTargetDate,
    sensitiveDays = this.sensitiveDays,
    cautionDays = this.cautionDays,
    recoveryDays = this.recoveryDays
)
