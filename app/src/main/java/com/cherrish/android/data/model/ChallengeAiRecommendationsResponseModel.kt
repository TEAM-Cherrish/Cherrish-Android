package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ChallengeAiRecommendationsResponseDto

data class ChallengesAiRecommendResponseModel(
    val routines: List<String>
)

fun ChallengeAiRecommendationsResponseDto.toModel() =
    ChallengesAiRecommendResponseModel(
        routines = routines
    )
