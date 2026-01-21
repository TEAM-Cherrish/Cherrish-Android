package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeCreateRoutineResponseDto(
    @SerialName("routineId")
    val routineId: Long,

    @SerialName("name")
    val name: String,

    @SerialName("scheduledDate")
    val scheduledDate: String,

    @SerialName("isComplete")
    val isComplete: Boolean
)
