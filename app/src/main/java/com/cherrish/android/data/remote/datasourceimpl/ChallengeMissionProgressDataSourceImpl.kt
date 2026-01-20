package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.ChallengeMissionProgressDataSource
import com.cherrish.android.data.remote.dto.response.ChallengeRoutineCompleteResponseDto
import com.cherrish.android.data.remote.service.ChallengeMissionProgressService
import javax.inject.Inject

class ChallengeMissionProgressDataSourceImpl @Inject constructor(
    private val challengeMissionService: ChallengeMissionProgressService
) : ChallengeMissionProgressDataSource {
    override suspend fun getChallengeMissions() = challengeMissionService.getChallengeMissions()
    override suspend fun patchChallengeRoutinesComplete(routineId: Long):
        BaseResponse<ChallengeRoutineCompleteResponseDto> {
        return challengeMissionService.patchChallengeRoutinesComplete(routineId = routineId)
    }
    override suspend fun postChallengeAdvanceDay() = challengeMissionService.postChallengeAdvanceDay()
}
