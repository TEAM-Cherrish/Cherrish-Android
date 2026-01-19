package com.cherrish.android.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengesHomecareRoutinesResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String?,
)

@Serializable
data class ChallengesAiRecommendResponseDto(
    @SerialName("data")
    val data: List<RoutinesDto>
)

@Serializable
data class RoutinesDto(
    @SerialName("routine")
    val routine : String
)
