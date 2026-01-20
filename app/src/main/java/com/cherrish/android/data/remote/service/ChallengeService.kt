package com.cherrish.android.data.remote.service

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.response.ChallengesAiRecommendResponseDto
import retrofit2.http.GET
import retrofit2.http.POST

interface ChallengeService{
//    @GET("/api/challenges/homecare-routines")
//    suspend fun getMainDashboard(): BaseResponse<ChallengeRoutineDto>

    @POST("/api/challenges/ai-recommendations")
    suspend fun postAiRecpmmendations(): BaseResponse<ChallengesAiRecommendResponseDto>
}
