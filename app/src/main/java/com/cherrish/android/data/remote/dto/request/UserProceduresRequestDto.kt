package com.cherrish.android.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProceduresRequestDto(
    @SerialName("scheduledAt")
    val scheduledAt: String,
    @SerialName("recoveryTargetDate")
    val recoveryTargetDate: String? = null,
    @SerialName("procedures")
    val procedures: List<UserProcedureItemDto>
)

@Serializable
data class UserProcedureItemDto(
    @SerialName("procedureId")
    val procedureId: Long,
    @SerialName("downtimeDays")
    val downtimeDays: Int
)
