package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.ChallengeDataSource
import com.cherrish.android.data.model.ChallengesHomecareRoutinesResponseModel
import com.cherrish.android.data.remote.dto.request.AiRecommendationRequestDto
import com.cherrish.android.data.remote.dto.response.ChallengesAiRecommendationsResponseDto
import com.cherrish.android.data.repository.ChallengeRepository
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDataSource: ChallengeDataSource
) : ChallengeRepository {
    override suspend fun getChallengeRoutinData(): Result<ChallengesHomecareRoutinesResponseModel> =
        suspendRunCatching {
            challengeDataSource.getHomecareRoutineData().data!!.toModel()
        }

    override suspend fun postChallengeAiRecommenData(
        request: AiRecommendationRequestDto
    ): Result<ChallengesAiRecommendationsResponseDto>
    = suspendRunCatching {
        challengeDataSource.postAiRecommendationsData(
            request = request.
        ).data!!.toModel()

    }
}
