package com.cherrish.android.data.repository

import com.cherrish.android.data.model.ChallengeCreateResponseModel
import com.cherrish.android.data.model.ChallengeHomecareRoutinesResponseModel
import com.cherrish.android.data.model.ChallengesAiRecommendResponseModel

interface ChallengeRepository {
    suspend fun getChallengeRoutineData(): Result<List<ChallengeHomecareRoutinesResponseModel>>

    suspend fun postAiRecommendations(
        homecareRoutineId: Int
    ): Result<ChallengesAiRecommendResponseModel>

    suspend fun postDemoChallenge(
        homecareRoutineId: Int,
        routineNames: List<String>
    ): Result<ChallengeCreateResponseModel>
}
