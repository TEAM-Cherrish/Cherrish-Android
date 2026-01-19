package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.response.ChallengesAiRecommendationsResponseDto

data class ChallengesAiRecommendationsResponseModel(
    val routines: List<String>
)

fun ChallengesAiRecommendationsResponseDto.toModel() = ChallengesAiRecommendationsResponseModel(
    routines = this.routines
)
