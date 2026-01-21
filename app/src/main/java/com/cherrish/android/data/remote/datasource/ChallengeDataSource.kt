package com.cherrish.android.data.remote.datasource

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.dto.request.ChallengeAiRecommendRequestDto
import com.cherrish.android.data.remote.dto.request.ChallengeCreateDataRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengeAiRecommendResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeCreateDataResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeHomecareRoutinesResponseDto

interface ChallengeDataSource {
    suspend fun getHomecareRoutineData(): BaseResponse<List<ChallengeHomecareRoutinesResponseDto>>

    suspend fun postAiRecommendations(
        request: ChallengeAiRecommendRequestDto
    ): BaseResponse<ChallengeAiRecommendResponseDto>

    suspend fun postDemoChallenge(
        request: ChallengeCreateDataRequestDto
    ): BaseResponse<ChallengeCreateDataResponseDto>
}
