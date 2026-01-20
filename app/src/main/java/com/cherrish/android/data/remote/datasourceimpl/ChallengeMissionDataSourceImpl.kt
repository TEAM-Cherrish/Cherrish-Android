package com.cherrish.android.data.remote.datasourceimpl

import com.cherrish.android.core.network.BaseResponse
import com.cherrish.android.data.remote.datasource.ChallengeMissionDataSource
import com.cherrish.android.data.remote.dto.response.ChallengeRoutineCompleteResponseDto
import com.cherrish.android.data.remote.service.ChallengeMissionService
import javax.inject.Inject

class ChallengeMissionDataSourceImpl @Inject constructor(
    private val challengeMissionService: ChallengeMissionService
) : ChallengeMissionDataSource {
    override suspend fun getChallengeMissions() = challengeMissionService.getChallengeMissions()
    override suspend fun patchChallengeRoutinesComplete(routineId: Long): BaseResponse<ChallengeRoutineCompleteResponseDto> {
        return challengeMissionService.patchChallengeRoutinesComplete(routineId = routineId)
    }
}
