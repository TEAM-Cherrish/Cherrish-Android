//ChallengeDataSourceImpl.kt
package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.ChallengeDataSource
import com.cherrish.android.data.remote.dto.request.ChallengeAiRecommendationRequestDto
import com.cherrish.android.data.remote.dto.request.ChallengeCreateRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengeCreateDataDto
import com.cherrish.android.data.remote.dto.response.ChallengeHomecareRoutinesResponseDto
import com.cherrish.android.data.remote.dto.response.ChallengesAiRecommendResponseDto
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
        request: ChallengeAiRecommendationRequestDto
    ): BaseResponse<ChallengesAiRecommendResponseDto> =
        challengeService.postAiRecommendations(request)

    override suspend fun postDemoChallenge(
        request: ChallengeCreateRequestDto
    ): BaseResponse<ChallengeCreateDataDto> =
        challengeService.postDemoChallenge(request)
}
