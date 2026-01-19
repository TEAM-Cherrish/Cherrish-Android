package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.AiRecommendationRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengesAiRecommendationsResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengesHomecareRoutinesResponseDto


interface ChallengeDataSource {
    suspend fun getHomecareRoutineData(): BaseResponse<ChallengesHomecareRoutinesResponseDto>

    suspend fun postAiRecommendationsData(
        request: AiRecommendationRequestDto
    ) : BaseResponse<ChallengesAiRecommendationsResponseDto>
}
