package com.cherrish.android.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiRecommendationRequestDto(
    @SerialName("homecareRoutineId")
    val homecareRoutineId: Int
)

@Serializable
data class ChallengeCreateRequestDto(
    @SerialName("homecareRoutineId")
    val homecareRoutineId: Int,

    @SerialName("routineNames")
    val routineNames: List<String>
)
