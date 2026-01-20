package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeRoutineCompleteResponseDto(
    @SerialName("routineId")
    val routineId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("isComplete")
    val isComplete: Boolean
)
