package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.CreatedUserProcedureDto
import com.cherrish.android.data.remote.dto.response.UserProceduresResponseDto
import java.time.LocalDate
import java.time.LocalDateTime

data class UserProceduresResponseModel(
    val procedures: List<CreatedUserProcedureModel>
)

data class CreatedUserProcedureModel(
    val userProcedureId: Long,
    val procedureId: Long,
    val procedureName: String,
    val scheduledAt: LocalDateTime,
    val downtimeDays: Int,
    val recoveryTargetDate: LocalDate
)

fun UserProceduresResponseDto.toModel(): UserProceduresResponseModel =
    UserProceduresResponseModel(
        procedures = procedures.map { it.toModel() }
    )

fun CreatedUserProcedureDto.toModel(): CreatedUserProcedureModel =
    CreatedUserProcedureModel(
        userProcedureId = this.userProcedureId,
        procedureId = this.procedureId,
        procedureName = this.procedureName,
        scheduledAt = this.scheduledAt,
        downtimeDays = this.downtimeDays,
        recoveryTargetDate = this.recoveryTargetDate
    )
