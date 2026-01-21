//ChallengeRequestDto
package com.cherrish.android.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiRecommendationRequestDto(
    @SerialName("homecareRoutineId")
    val homecareRoutineId: Int
) // 얘 안씀 확인 필요

@Serializable
data class ChallengeCreateRequestDto(
    @SerialName("homecareRoutineId")
    val homecareRoutineId: Int,

    @SerialName("routineNames")
    val routineNames: List<String>
)
