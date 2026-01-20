package com.cherrish.android.data.repository

import com.cherrish.android.data.model.ChallengeMissionResponseModel
import com.cherrish.android.data.model.ChallengeRoutineCompleteResponseModel

interface ChallengeMissionRepository {
    suspend fun getChallengeMissions(): Result<ChallengeMissionResponseModel>
    suspend fun patchChallengeRoutinesComplete(routineId: Long): Result<ChallengeRoutineCompleteResponseModel>
}
