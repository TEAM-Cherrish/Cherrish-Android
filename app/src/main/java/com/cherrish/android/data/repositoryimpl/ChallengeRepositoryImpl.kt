package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.ChallengeDataSource
import com.cherrish.android.data.model.ChallengeHomecareRoutinesResponseModel
import com.cherrish.android.data.repository.ChallengeRepository
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDataSource: ChallengeDataSource
) : ChallengeRepository {
    override suspend fun getChallengeRoutineData()
    : Result<ChallengeHomecareRoutinesResponseModel> =
        suspendRunCatching {
          challengeDataSource.getHomecareRoutineData().data!!.toModel()

        }
}
