
package com.cherrish.android.data.repository

import com.cherrish.android.data.remote.dto.request.AiRecommendationRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengesAiRecommendationsResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengesHomecareRoutinesResponseDto

interface ChallengeRepository{
    suspend fun getChallengeRoutinData(

    ): Result<ChallengesHomecareRoutinesResponseDto>

    suspend fun postChallengeAiRecommenData(
        request: AiRecommendationRequestDto
    ): Result<ChallengesAiRecommendationsResponseDto>
}
