package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.ChallengeAiRecommendationRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengeAiRecommendationsResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ChallengeService {
    @POST("/api/challenges/ai-recommendations")
    suspend fun postAiRecpmmendations(
        @Body request: ChallengeAiRecommendationRequestDto
    ): BaseResponse<ChallengeAiRecommendationsResponseDto>
}
