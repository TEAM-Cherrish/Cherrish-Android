package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeCreateDataResponseDto(
    @SerialName("challengeId")
    val challengeId: Long,

    @SerialName("title")
    val title: String,

    @SerialName("totalDays")
    val totalDays: Int,

    @SerialName("startDate")
    val startDate: String,

    @SerialName("endDate")
    val endDate: String,

    @SerialName("totalRoutineCount")
    val totalRoutineCount: Int,

    @SerialName("routines")
    val routines: List<ChallengeCreateRoutineResponseDto>
)
