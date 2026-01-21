//ChallengeService.kt
package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.ChallengeAiRecommendationRequestDto
import com.cherrish.android.data.remote.dto.request.ChallengeCreateRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengeCreateDataDto
import com.cherrish.android.data.remote.dto.response.ChallengeHomecareRoutinesResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengesAiRecommendResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChallengeService {
    @GET("api/challenges/homecare-routines")
    suspend fun getHomecareRoutineData(): BaseResponse<List<ChallengeHomecareRoutinesResponseDto>>

    @POST("api/challenges/ai-recommendations")
    suspend fun postAiRecommendations(
        @Body request: ChallengeAiRecommendationRequestDto
    ): BaseResponse<ChallengesAiRecommendResponseDto>

    @POST("api/demo/challenges")
    suspend fun postDemoChallenge(
        @Body request: ChallengeCreateRequestDto
    ): BaseResponse<ChallengeCreateDataDto>
}
