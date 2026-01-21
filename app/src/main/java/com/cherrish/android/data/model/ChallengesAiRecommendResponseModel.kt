package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ChallengeAiRecommendResponseDto

data class ChallengesAiRecommendResponseModel(
    val routines: List<String>
)

fun ChallengeAiRecommendResponseDto.toModel() =
    ChallengesAiRecommendResponseModel(
        routines = this.routines
    )
