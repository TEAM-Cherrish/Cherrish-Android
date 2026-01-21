package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.ChallengeDataSource
import com.cherrish.android.data.remote.dto.request.ChallengeAiRecommendRequestDto
import com.cherrish.android.data.remote.dto.request.ChallengeCreateDataRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengeAiRecommendResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeCreateDataResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengeHomecareRoutinesResponseDto
import com.cherrish.android.data.remote.service.ChallengeService
import javax.inject.Inject

class ChallengeDataSourceImpl @Inject constructor(
    private val challengeService: ChallengeService
) : ChallengeDataSource {
    override suspend fun getHomecareRoutineData():
        BaseResponse<List<ChallengeHomecareRoutinesResponseDto>> {
        return challengeService.getHomecareRoutineData()
    }

    override suspend fun postAiRecommendations(
        request: ChallengeAiRecommendRequestDto
    ): BaseResponse<ChallengeAiRecommendResponseDto> =
        challengeService.postAiRecommendations(request)

    override suspend fun postDemoChallenge(
        request: ChallengeCreateDataRequestDto
    ): BaseResponse<ChallengeCreateDataResponseDto> =
        challengeService.postDemoChallenge(request)
}
