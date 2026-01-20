package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengesAiRecommendResponseDto(
    @SerialName("data")
    val data: ChallengesAiRecommendDataDto
)

@Serializable
data class ChallengesAiRecommendDataDto(
    @SerialName("routines")
    val routines: List<String>
)
