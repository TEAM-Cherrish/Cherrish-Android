package com.cherrish.android.data.repositoryimpl

import com.cherrish.android.core.util.suspendRunCatching
import com.cherrish.android.data.model.toModel
import com.cherrish.android.data.remote.datasource.ChallengeDataSource
import com.cherrish.android.data.repository.DummyRepository
import com.cherrish.android.data.model.ChallengeRoutineResponseModel
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDataSource: ChallengeDataSource
) : DummyRepository {
    override suspend fun getChallengeRoutinData(): Result<ChallengeRoutineResponseModel> =
        suspendRunCatching {
            challengeDataSource.getHomecareRoutineData().data!!.toModel()
                .getDummyData().data!!.toModel()
        }
}
