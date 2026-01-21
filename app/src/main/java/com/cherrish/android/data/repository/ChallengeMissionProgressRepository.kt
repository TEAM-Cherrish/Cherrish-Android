package com.cherrish.android.data.repository

import com.cherrish.android.data.model.ChallengeMissionProgressResponseModel
import com.cherrish.android.data.model.ChallengeRoutineCompleteResponseModel

interface ChallengeMissionProgressRepository {
    suspend fun getChallengeMissions(): Result<ChallengeMissionProgressResponseModel>
    suspend fun patchChallengeRoutinesComplete(routineId: Long):
        Result<ChallengeRoutineCompleteResponseModel>
    suspend fun postChallengeAdvanceDay(): Result<ChallengeMissionProgressResponseModel>

    suspend fun hasChallengeRegistered(): Result<Boolean>
}
