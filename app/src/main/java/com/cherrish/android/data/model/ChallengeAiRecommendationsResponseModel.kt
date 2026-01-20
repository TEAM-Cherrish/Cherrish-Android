package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ChallengesAiRecommendResponseDto


data class ChallengesAiRecommendResponseModel(
    val routines: List<String>
)

fun ChallengesAiRecommendResponseDto.toModel() =
    ChallengesAiRecommendResponseModel(
        routines = routines
    )
