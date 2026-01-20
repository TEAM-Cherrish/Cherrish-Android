package com.cherrish.android.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeAiRecommendationRequestDto(
    @SerialName("homecareRoutineId")
    val homecareRoutineId: Int
)
