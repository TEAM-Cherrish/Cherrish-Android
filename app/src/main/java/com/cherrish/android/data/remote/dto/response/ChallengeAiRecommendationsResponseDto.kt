package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeAiRecommendationsResponseDto(
    @SerialName("routines")
    val routines: List<String>
)
