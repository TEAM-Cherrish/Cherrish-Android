package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProceduresResponseDto(
    @SerialName("procedures")
    val procedures: List<CreatedUserProcedureDto>
)

@Serializable
data class CreatedUserProcedureDto(
    @SerialName("userProcedureId")
    val userProcedureId: Long,
    @SerialName("procedureId")
    val procedureId: Long,
    @SerialName("procedureName")
    val procedureName: String,
    @SerialName("scheduledAt")
    val scheduledAt: String,
    @SerialName("downtimeDays")
    val downtimeDays: Int,
    @SerialName("recoveryTargetDate")
    val recoveryTargetDate: String
)
