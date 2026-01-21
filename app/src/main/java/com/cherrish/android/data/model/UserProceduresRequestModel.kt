package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.request.UserProcedureItemDto
import com.cherrish.android.data.remote.dto.request.UserProceduresRequestDto
import java.time.LocalDate
import java.time.LocalDateTime

data class UserProceduresRequestModel(
    val scheduledAt: LocalDateTime,
    val recoveryTargetDate: LocalDate? = null,
    val procedures: List<UserProcedureItemModel>
)

data class UserProcedureItemModel(
    val procedureId: Long,
    val downtimeDays: Int
)

fun UserProceduresRequestModel.toDto(): UserProceduresRequestDto =
    UserProceduresRequestDto(
        scheduledAt = this.scheduledAt,
        recoveryTargetDate = this.recoveryTargetDate,
        procedures = this.procedures.map { UserProcedureItemDto(it.procedureId, it.downtimeDays) }
    )
