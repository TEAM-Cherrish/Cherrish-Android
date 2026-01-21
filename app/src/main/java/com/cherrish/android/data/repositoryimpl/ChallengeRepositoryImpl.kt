//ChallengeRepositoryImpl.kt
package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.ChallengeCreateResponseModel
import com.cherrish.android.data.model.ChallengeHomecareRoutinesResponseModel
import com.cherrish.android.data.model.ChallengesAiRecommendResponseModel
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.ChallengeDataSource
import com.cherrish.android.data.remote.dto.request.ChallengeAiRecommendationRequestDto
import com.cherrish.android.data.remote.dto.request.ChallengeCreateRequestDto
import com.cherrish.android.data.repository.ChallengeRepository
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDataSource: ChallengeDataSource
) : ChallengeRepository {
    override suspend fun getChallengeRoutineData():
            Result<List<ChallengeHomecareRoutinesResponseModel>> =
        suspendRunCatching {
            challengeDataSource
                .getHomecareRoutineData()
                .data
                ?.map { it.toModel() }
                ?: emptyList()
        }

    override suspend fun postAiRecommendations(
        homecareRoutineId: Int
    ): Result<ChallengesAiRecommendResponseModel> =
        suspendRunCatching {
            challengeDataSource
                .postAiRecommendations(
                    ChallengeAiRecommendationRequestDto(
                        homecareRoutineId = homecareRoutineId
                    )
                )
                .data
                ?.toModel()
                ?: throw IllegalStateException("Empty AI recommendation response")
        }

    override suspend fun postDemoChallenge(
        homecareRoutineId: Int,
        routineNames: List<String>
    ): Result<ChallengeCreateResponseModel> =
        suspendRunCatching {
            challengeDataSource
                .postDemoChallenge(
                    ChallengeCreateRequestDto(
                        homecareRoutineId = homecareRoutineId,
                        routineNames = routineNames
                    )
                )
                .data
                ?.toModel()
                ?: throw IllegalStateException("Empty challenge creation response")
        }
}
