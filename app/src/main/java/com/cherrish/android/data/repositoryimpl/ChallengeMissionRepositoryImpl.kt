package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.ChallengeMissionResponseModel
import com.cherrish.android.data.model.ChallengeRoutineCompleteResponseModel
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.ChallengeMissionDataSource
import com.cherrish.android.data.repository.ChallengeMissionRepository
import javax.inject.Inject

class ChallengeMissionRepositoryImpl @Inject constructor(
    private val challengeMissionDataSource: ChallengeMissionDataSource
) : ChallengeMissionRepository {
    override suspend fun getChallengeMissions(): Result<ChallengeMissionResponseModel> =
        suspendRunCatching {
            challengeMissionDataSource.getChallengeMissions().data!!.toModel()
        }

    override suspend fun patchChallengeRoutinesComplete(routineId: Long): Result<ChallengeRoutineCompleteResponseModel> =
        suspendRunCatching {
            challengeMissionDataSource.patchChallengeRoutinesComplete(routineId = routineId).data!!.toModel()
        }
}
