package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.ChallengeAiRecommendRequestDto
import com.cherrish.android.data.remote.dto.request.ChallengeCreateDataRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengeAiRecommendResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeCreateDataResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeHomecareRoutinesResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChallengeService {
    @GET("api/challenges/homecare-routines")
    suspend fun getHomecareRoutineData(): BaseResponse<List<ChallengeHomecareRoutinesResponseDto>>

    @POST("api/challenges/ai-recommendations")
    suspend fun postAiRecommendations(
        @Body request: ChallengeAiRecommendRequestDto
    ): BaseResponse<ChallengeAiRecommendResponseDto>

    @POST("api/demo/challenges")
    suspend fun postDemoChallenge(
        @Body request: ChallengeCreateDataRequestDto
    ): BaseResponse<ChallengeCreateDataResponseDto>
}
