package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.ChallengeAiRecommendationRequestDto
import com.cherrish.android.data.remote.dto.request.ChallengeCreateRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengeCreateDataDto
import com.cherrish.android.data.remote.dto.response.ChallengeHomecareRoutinesResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengesAiRecommendResponseDto

interface ChallengeDataSource {
    suspend fun getHomecareRoutineData(): BaseResponse<List<ChallengeHomecareRoutinesResponseDto>>

    suspend fun postAiRecommendations(
        request: ChallengeAiRecommendationRequestDto
    ): BaseResponse<ChallengesAiRecommendResponseDto>

    suspend fun postDemoChallenge(
        request: ChallengeCreateRequestDto
    ): BaseResponse<ChallengeCreateDataDto>
}
