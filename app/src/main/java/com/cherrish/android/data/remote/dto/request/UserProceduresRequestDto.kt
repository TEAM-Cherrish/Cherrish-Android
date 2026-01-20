package com.cherrish.android.data.remote.dto.request

import com.cherrish.android.core.util.LocalDateSerializer
import com.cherrish.android.core.util.LocalDateTimeSerializer
import java.time.LocalDate
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProceduresRequestDto(
    @SerialName("scheduledAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val scheduledAt: LocalDateTime,
    @SerialName("recoveryTargetDate")
    @Serializable(with = LocalDateSerializer::class)
    val recoveryTargetDate: LocalDate? = null,
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
