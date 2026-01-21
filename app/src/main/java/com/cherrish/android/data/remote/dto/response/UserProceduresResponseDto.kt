package com.cherrish.android.data.remote.dto.response

import com.cherrish.android.core.util.LocalDateSerializer
import com.cherrish.android.core.util.LocalDateTimeSerializer
import java.time.LocalDate
import java.time.LocalDateTime
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
    @Serializable(with = LocalDateTimeSerializer::class)
    val scheduledAt: LocalDateTime,
    @SerialName("downtimeDays")
    val downtimeDays: Int,
    @SerialName("recoveryTargetDate")
    @Serializable(with = LocalDateSerializer::class)
    val recoveryTargetDate: LocalDate
)
