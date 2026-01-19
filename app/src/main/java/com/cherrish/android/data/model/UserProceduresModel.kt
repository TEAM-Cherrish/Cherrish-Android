package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.request.UserProcedureItemDto
import com.cherrish.android.data.remote.dto.request.UserProceduresRequestDto
import com.cherrish.android.data.remote.dto.response.CreatedUserProcedureDto
import com.cherrish.android.data.remote.dto.response.UserProceduresResponseDto

data class UserProceduresRequestModel(
    val scheduledAt: String,
    val recoveryTargetDate: String? = null,
    val procedures: List<UserProcedureItemModel>
)

data class UserProcedureItemModel(
    val procedureId: Long,
    val downtimeDays: Int
)

fun UserProceduresRequestModel.toDto(): UserProceduresRequestDto =
    UserProceduresRequestDto(
        scheduledAt = scheduledAt,
        recoveryTargetDate = recoveryTargetDate,
        procedures = procedures.map { UserProcedureItemDto(it.procedureId, it.downtimeDays) }
    )

data class UserProceduresResponseModel(
    val procedures: List<CreatedUserProcedureModel>
)

data class CreatedUserProcedureModel(
    val userProcedureId: Long,
    val procedureId: Long,
    val procedureName: String,
    val scheduledAt: String,
    val downtimeDays: Int,
    val recoveryTargetDate: String
)

fun UserProceduresResponseDto.toModel(): UserProceduresResponseModel =
    UserProceduresResponseModel(
        procedures = procedures.map { it.toModel() }
    )

fun CreatedUserProcedureDto.toModel(): CreatedUserProcedureModel =
    CreatedUserProcedureModel(
        userProcedureId = userProcedureId,
        procedureId = procedureId,
        procedureName = procedureName,
        scheduledAt = scheduledAt,
        downtimeDays = downtimeDays,
        recoveryTargetDate = recoveryTargetDate
    )
