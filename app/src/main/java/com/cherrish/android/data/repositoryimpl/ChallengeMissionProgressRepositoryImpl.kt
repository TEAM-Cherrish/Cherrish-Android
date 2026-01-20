package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.ChallengeMissionProgressResponseModel
import com.cherrish.android.data.model.ChallengeRoutineCompleteResponseModel
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.ChallengeMissionProgressDataSource
import com.cherrish.android.data.repository.ChallengeMissionProgressRepository
import javax.inject.Inject

class ChallengeMissionProgressRepositoryImpl @Inject constructor(
    private val challengeMissionDataSource: ChallengeMissionProgressDataSource
) : ChallengeMissionProgressRepository {
    override suspend fun getChallengeMissions(): Result<ChallengeMissionProgressResponseModel> =
        suspendRunCatching {
            challengeMissionDataSource.getChallengeMissions().data!!.toModel()
        }

    override suspend fun patchChallengeRoutinesComplete(routineId: Long):
        Result<ChallengeRoutineCompleteResponseModel> =
        suspendRunCatching {
            challengeMissionDataSource.patchChallengeRoutinesComplete(
                routineId = routineId
            ).data!!.toModel()
        }
}
